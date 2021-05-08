package com.alekseytyan.logiweb.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Around("@annotation(LogAnnotation)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        logger.info(joinPoint.getTarget().getClass().getName() + "." +
                joinPoint.getSignature() + "(" + Arrays.toString(joinPoint.getArgs()) + ")" +
                " returns " + proceed +
                ", executed in " + executionTime + "ms");

        return proceed;
    }

    @AfterThrowing(
            pointcut = "execution(* com.alekseytyan.logiweb.service..*.*(..)) " +
                    "|| execution(* com.alekseytyan.logiweb.controller..*.*(..))",
            throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        if (logger.isErrorEnabled()) {
            logger.error(
                    joinPoint.getTarget().getClass().getName() + "." +
                    joinPoint.getSignature().getName() +
                    "(" + Arrays.toString(joinPoint.getArgs()) + ")" +
                    " Exception : " + error,
                    error
            );
        }
    }

}
