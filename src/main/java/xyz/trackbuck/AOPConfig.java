package xyz.trackbuck;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import xyz.trackbuck.aop.RefCleanerInterceptor;

/**
 * Created by lex on 09.10.16.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"xyz.trackbuck.aop.**"})
public class AOPConfig {

    @Autowired
    RefCleanerInterceptor refCleanerInterceptor;

    @Bean
    public Advisor debugAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* xyz.trackbuck.rest..*(..)) && (@annotation(org.springframework.web.bind.annotation.RequestMapping))");
        return new DefaultPointcutAdvisor(pointcut, refCleanerInterceptor);
    }

}

