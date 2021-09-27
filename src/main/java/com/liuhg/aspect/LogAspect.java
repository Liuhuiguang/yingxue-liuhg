package com.liuhg.aspect;

import com.liuhg.annotation.AddLog;
import com.liuhg.dao.LogMapper;
import com.liuhg.entity.Admin;
import com.liuhg.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Aspect
@Configuration
public class LogAspect {
    @Autowired
    HttpSession session;
    @Autowired
    LogMapper logDao;

    @Around("@annotation(com.liuhg.annotation.AddLog)")
    public Object addLog(ProceedingJoinPoint proceedingJoinPoint) {
        String name = proceedingJoinPoint.getSignature().getName();
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        System.out.println(signature);
        Method method = signature.getMethod();
        AddLog annotation = method.getAnnotation(AddLog.class);
        System.out.println(annotation);
        Admin admin = (Admin) session.getAttribute("Admin");
        String message;
        Object proceed = null;
        try {
            proceed = proceedingJoinPoint.proceed();
            message = "执行成功";
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            message = "执行失败";

        }
        String uuid = UUID.randomUUID().toString();
        String logMethod = annotation.value().toString();
        Log log = new Log(uuid, admin.getUsername(), new Date(), logMethod, message);
        logDao.insert(log);
        return proceed;
    }
}
