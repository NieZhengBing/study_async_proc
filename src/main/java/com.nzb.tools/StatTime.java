package com.nzb.tools;


import com.nzb.vo.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author M
 * @create 2018/2/1
 */
public class StatTime {
    private Logger logger = LoggerFactory.getLogger(StatTime.class);

    private User user;

    public StatTime() {
        logger.info("****************Aop start");
    }

    @Pointcut("execution(* com.nzb.service.impl.*.*.*Register(..))")
    public void start() {}

    @Around("stat()&&args(user)")
    public Object startTime(ProceedingJoinPoint proceedingJoinPoint, User user) {
        this.user = user;
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed(new Object[]{this.user});
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        logger.info("*************spend time: " + (System.currentTimeMillis() -start) + "ms");
        return result;
    }
}
