package xyz.trackbuck.aop;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.trackbuck.aop.CircularRefCleaner;

/**
 * Created by akalina on 26.06.2015.
 */

@Component
public class RefCleanerInterceptor implements MethodInterceptor {

    @Autowired
    CircularRefCleaner cleaner;


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        return CircularRefCleaner.clean(methodInvocation.proceed());
    }
}
