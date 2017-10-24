package com.jzli.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * =======================================================
 *
 * @Company 金色家网络科技有限公司-云存储业务部
 * @Date ：2016/9/29
 * @Author ：li.jinzhao
 * @Version ：0.0.1
 * @Description ：使用AOP将接口访问记录写入日志
 * ========================================================
 */
@Aspect
@Component
@Order(1)
public class AccessLogAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private ThreadLocal<Long> startTimeRecorder = new ThreadLocal<>();


    @Pointcut("execution(public * com.jzli.controller..*.*(..))")
    public void access() {
    }

    @Before("access()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        //记录开始时间
        startTimeRecorder.set(System.currentTimeMillis());
        //显示请求参数
        showRequestParameter(joinPoint);
    }

    @AfterReturning(returning = "result", pointcut = "access()")
    public void doAfterReturning(Object result) throws Throwable {
        if (!ObjectUtils.isEmpty(startTimeRecorder)) {
            // 处理完请求，返回结果
            logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<After<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            if (!ObjectUtils.isEmpty(result)) {
                String s = result.toString();
                if (!ObjectUtils.isEmpty(s)) {
                    if (s.length() < 1000) {
                        logger.info("RESPONSE RESULT: " + s);
                    } else {
                        logger.info("RESPONSE RESULT: " + s.substring(0, 1000));
                    }
                }
            }
        }
        long time = System.currentTimeMillis() - startTimeRecorder.get();
        logger.info("SPEND TIME : " + time + "ms");
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>After>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }


    /**
     * 展示请求参数
     *
     * @param joinPoint
     */
    private void showRequestParameter(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ObjectUtils.isEmpty(attributes)) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        String version = request.getHeader("version");
        String versionCode = request.getHeader("versionCode");
        String accessToken = request.getHeader("accessToken");
        String deviceType = request.getHeader("deviceType");
        String signature = request.getHeader("signature");
        String userName = request.getHeader("userName");
        String platformId = request.getHeader("platformId");
        String productId = request.getHeader("productId");
        String channelId = request.getHeader("channelId");
        String channelCode = platformId + productId + channelId;
        String deviceId = request.getHeader("deviceId");
        String ip = request.getRemoteAddr();
        String url = request.getRequestURL().toString();


        // 记录下请求内容
        logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<Before<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("header version:" + version);
        logger.info("header versionCode:" + versionCode);
        logger.info("header accessToken:" + accessToken);
        logger.info("header deviceType:" + deviceType);
        logger.info("header signature:" + signature);
        logger.info("header userName:" + userName);
        logger.info("header platformId:" + platformId);
        logger.info("header productId:" + productId);
        logger.info("header channelId:" + channelId);
        logger.info("header channelCode:" + channelCode);
        logger.info("header deviceId:" + deviceId);
        //去掉参数超过1000的日志记录
        String requestStr = Arrays.toString(joinPoint.getArgs());
        if (!ObjectUtils.isEmpty(requestStr) && requestStr.length() < 1000) {
            logger.info("REQUEST ARGS : " + requestStr);
        } else {
            logger.info("REQUEST ARGS : " + requestStr.substring(0, 1000));
        }
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>Before>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
}