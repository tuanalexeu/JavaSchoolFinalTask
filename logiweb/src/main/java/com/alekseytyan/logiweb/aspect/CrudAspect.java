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

@Aspect
@Component
public class CrudAspect {

    private static final Logger logger = LoggerFactory.getLogger(CrudAspect.class);

    private final DataSourceEventPublisher publisher;

    @Autowired
    public CrudAspect(DataSourceEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Around("@annotation(CrudAnnotation)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        logger.info(joinPoint.getTarget().getClass().getName() + "." +
                joinPoint.getSignature() + "(" + Arrays.toString(joinPoint.getArgs()) + ")" +
                " returns " + proceed +
                ", executed in " + executionTime + "ms");


        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        CrudAnnotation crudAnnotation = method.getAnnotation(CrudAnnotation.class);
        publisher.publishEvent(crudAnnotation.code());

        return proceed;
    }
}
