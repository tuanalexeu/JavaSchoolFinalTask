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
import java.util.Formatter;

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Around("@annotation(LogAnnotation)")
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

        return proceed;
    }

    @AfterThrowing(
            pointcut = "execution(* com.alekseytyan.logiweb.service..*.*(..)) " +
                    "|| execution(* com.alekseytyan.logiweb.controller..*.*(..))",
            throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        if (logger.isErrorEnabled()) {
            try(Formatter formatter = new Formatter()) {
                logger.error(formatter.format(
                        "%s %s(%s). Exception: %s",
                        joinPoint.getTarget().getClass().getSimpleName(),
                        joinPoint.getSignature(),
                        Arrays.toString(joinPoint.getArgs()),
                        error
                ).toString());
            }
        }
    }

}
