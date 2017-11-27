package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Descpription: Describe the function of class
 * @Author: Created by xucheng.
 */

@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    /**
     * 拦截GirlController类中所有方法
     */
    @Pointcut("execution(public * com.example.demo.controller.GirlController.*(..))")
    public void log() {

    }

    /**
     *在要拦截的方法执行前执行
     * @param joinPoint
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("Before...");

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();

//        URL
        logger.info("url={}",request.getRequestURL());

//        mehtod
        logger.info("method={}",request.getMethod());

//        ip
        logger.info("ip={}",request.getRemoteAddr());

//        类方法
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());

//        参数
        logger.info("args={}",joinPoint.getArgs());
    }

    /**
     * 在要拦截的方法执行后执行
     */
    @After("log()")
    public void doAfter() {
        logger.info("After...");
    }

    /**
     * 记录方法返回的对象
     * @param object
     */
    @AfterReturning(returning = "object",pointcut = "log()")
    public void doAfterRetruning(Object object) {
        if (!object.toString().isEmpty())
            logger.info("response={}", object.toString());

    }
}
