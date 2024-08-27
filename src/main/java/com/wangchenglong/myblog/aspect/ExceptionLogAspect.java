package com.wangchenglong.myblog.aspect;

import com.wangchenglong.myblog.model.entity.ExceptionLog;
import com.wangchenglong.myblog.service.ExceptionLogService;
import com.wangchenglong.myblog.utils.*;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static cn.hutool.core.util.NumberUtil.add;

/**
 * @Author: Wangchenglong
 * @Date: 2024/8/23 15:03
 * @Description:
 */
@Component
@Aspect
public class ExceptionLogAspect {
    @Resource
    ExceptionLogService exceptionLogService;
    @Resource
    UserAgentAnalyzer userAgentAnalyzer;

    @Pointcut(value = "execution(* com.wangchenglong.myblog.controller..*.*(..))")
    public void logPointcut() {

    }

    @AfterThrowing(value = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Exception e) {
        ExceptionLog exceptionLog = handleExceptionLog(joinPoint, e);
        //System.out.println(".........." + exceptionLog);
        exceptionLogService.save(exceptionLog);
    }

    public ExceptionLog handleExceptionLog(JoinPoint joinPoint, Exception e) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String ip = IpAddressUtils.getIpAddress(request);
        String cityInfo = IpAddressUtils.getCityInfo(ip);
        String userAgent = request.getHeader("User-Agent");
        String agentNameVersion = userAgentAnalyzer.parse(userAgent).getValue("AgentNameVersion");
        String agentName = userAgentAnalyzer.parse(userAgent).getValue("AgentName");
        String description = joinPoint.toString();
        String error = StringUtils.getStackTrace(e);
        ExceptionLog exceptionLog = new ExceptionLog(uri, method, description, error, ip, userAgent);
        exceptionLog.setOs(agentNameVersion);
        exceptionLog.setBrowser(agentName);
        exceptionLog.setIpSource(cityInfo);
        Map<String, Object> requestParams = AopUtils.getRequestParams(joinPoint);
        exceptionLog.setParams(StringUtils.substring(JacksonUtils.writeValueAsString(requestParams), 0, 2000));
        Long userId = null;
        if (uri.startsWith("/admin") || uri.startsWith("/sys")) {
            String token = request.getHeader("token");
            userId = JWTUtils.getUserInfo(token);
        } else if (uri.startsWith("/api") || uri.startsWith("/test")) {
            userId = Long.valueOf(requestParams.get("id").toString());
            //System.out.println(">>>"+userId);
        }
        if (userId != null) {
            exceptionLog.setUserId(userId);
        }
        return exceptionLog;
    }

}