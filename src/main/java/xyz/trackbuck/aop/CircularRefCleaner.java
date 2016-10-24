package xyz.trackbuck.aop;


import com.google.common.base.Strings;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Hibernate;
import org.hibernate.LazyInitializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import xyz.trackbuck.util.MiscUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Своровано
 */
@Component
public class CircularRefCleaner {
	private static Logger logger = LoggerFactory.getLogger(CircularRefCleaner.class);

	private static final String PACKAGE_PREFIX = "xyz.trackbuck";

	public static <T> T clean(T root){
		if (root == null)
			return null;

		logger.debug("Cleaning object {} of type {}", root, root.getClass());

		if (Iterable.class.isInstance(root))			
			cleanList((Iterable) root);
		else
			clean(root, new HashSet<>(), 1);

		return root;
	}

	public static  <T, I extends Iterable<T>> I cleanList(I roots){
		for (Object root : roots) {
			clean(root);
		}
		return roots;
	}

	private static void clean(Object owner, Set<Object> knownObjects, int level) {


		knownObjects.add(owner);

		Class<?> rootType = owner.getClass();

		List<Object> nestedObjects = new ArrayList<>();

		Map<String, String> props = null;
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(owner.getClass());

			for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
				if (propertyDescriptor.getName().equals("class") && propertyDescriptor.getPropertyType().equals(Class.class))
					continue;
				Class<?> propertyType = propertyDescriptor.getPropertyType();
				if (MiscUtils.isSimpleType(propertyType))
					continue;

				Object value;
				try {
					value = PropertyUtils.getProperty(owner, propertyDescriptor.getName());
				}
				catch (InvocationTargetException e){
					if (e.getTargetException().getClass() == LazyInitializationException.class)
						continue;
					else
						throw e;
				}

				if (value == null)
					continue;

				if (Iterable.class.isInstance(value)) {
					Iterable iterable = (Iterable) value;
					if (Hibernate.isInitialized(iterable)){
						for (Object o : iterable) {
							if (o != null)
								nestedObjects.add(o);
						}
					}
				}
				else {
					nestedObjects.add(value);
				}
			}
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | IntrospectionException e) {
			e.printStackTrace();
		}

		for (Object o : nestedObjects) {
			if (!isModelType(o.getClass()))
				continue;

			if (!Hibernate.isInitialized(o))
				continue;
			clearBackRef(o, knownObjects, level);
			clean(o, knownObjects, level + 1);
		}

		knownObjects.remove(owner);
	}

	private static void clearBackRef(Object obj, Set<Object> backRefs, int level){
		logger.debug("{}Clearing back references for object {} at level {}", Strings.repeat("\t", level),
			shortToString(obj), obj.getClass().getSimpleName(), level);

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());

			for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
				if (MiscUtils.isSimpleType(propertyDescriptor.getPropertyType()))
					continue;

				Object propertyValue;
				String propertyName = propertyDescriptor.getName();

				try {
					propertyValue = PropertyUtils.getProperty(obj, propertyName);
				}
				catch (InvocationTargetException e){
					if (e.getTargetException().getClass() == LazyInitializationException.class)
						continue;
					else
						throw e;
				}

				if (propertyValue == null)
					continue;

				if (!Hibernate.isInitialized(propertyValue))
					continue;

				if (Collection.class.isInstance(propertyValue)){
					Collection collection = (Collection) propertyValue;
					boolean removal = collection.remove(backRefs);
					if (removal) {
						logger.debug("{}Removing back reference to object {} from collection at field {} of object {}", Strings.repeat("\t", level + 1),
							shortToString(propertyValue), propertyName, shortToString(obj));
					}
				}
				else {
					if (backRefs.contains(propertyValue)){
						PropertyUtils.setProperty(obj, propertyName, null);

						logger.debug("{}Removing back reference to object {} at field {}  of object {}", Strings.repeat("\t", level + 1),
							shortToString(propertyValue), propertyName, shortToString(obj));
					}
				}
			}
		} catch (
			  IllegalAccessException
			| InvocationTargetException
			| NoSuchMethodException
			| IntrospectionException e) {
			throw new RuntimeException(e);
		}
	}

	private static String shortToString(Object o){
		String[] split = o.toString().split("\\.");
		return split[split.length - 1];
	}

	private static boolean isModelType(Class clazz){
		return clazz.getName().startsWith(PACKAGE_PREFIX);
	}
}
