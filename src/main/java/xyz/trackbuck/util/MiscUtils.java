package xyz.trackbuck.util;


import java.util.*;


public class MiscUtils {
	private static final Set<Class> SIMPLE_TYPES = new HashSet<Class>(Arrays.asList(
		String.class, Date.class, Calendar.class, Boolean.class, Character.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, Void.class));



	public static String uuid(){
		return UUID.randomUUID().toString().replace("-", "");
	}




	public static boolean isSimpleType(Class clazz){
		return clazz.isPrimitive() || clazz.isEnum() || SIMPLE_TYPES.contains(clazz);
	}


}
