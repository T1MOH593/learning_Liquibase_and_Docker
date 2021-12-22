package by.vlad.liquibase_starter.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Aspect
@Component
public class UserControllerLoggingAspect {

    @Around("execution(* by.vlad.liquibase_starter.service.UserService.*(..))")
    public Object logAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        var signature = proceedingJoinPoint.getSignature();
        System.out.println("Method " + signature.getName() + " gets invoked " + LocalTime.now());

        var l = System.currentTimeMillis();
        var object = proceedingJoinPoint.proceed();
        System.out.println("Method " + signature.getName() + " took " + (System.currentTimeMillis() - l) + " milliSeconds");

        return object;
    }
}
