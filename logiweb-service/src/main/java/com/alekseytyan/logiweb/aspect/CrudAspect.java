package com.alekseytyan.logiweb.aspect;

import com.alekseytyan.logiweb.listener.datasource.DataSourceEventPublisher;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Formatter;

/**
 * Aspect is used to send messages to the 2nd application
 */
@Aspect
@Component
public class CrudAspect {

    private static final Logger logger = LoggerFactory.getLogger(CrudAspect.class);

    private final DataSourceEventPublisher publisher;

    @Autowired
    public CrudAspect(DataSourceEventPublisher publisher) {
        this.publisher = publisher;
    }

    /**
     * Method get called after particular CRUD operations are committed and send message to 2nd app
     * @param joinPoint - join point to execute
     * @return - result of invokation
     * @throws Throwable - in case of any exception
     */
    @Around("@annotation(CrudAnnotation)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        try(Formatter formatter = new Formatter()) {
            logger.info(formatter.format(
                    "%s %s(%s) returns %s, in %dms",
                    joinPoint.getTarget().getClass().getSimpleName(),
                    joinPoint.getSignature(),
                    Arrays.toString(joinPoint.getArgs()),
                    proceed,
                    executionTime
            ).toString());
        }


        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        CrudAnnotation crudAnnotation = method.getAnnotation(CrudAnnotation.class);

        publisher.publishEvent(crudAnnotation.code());

        return proceed;
    }
}
