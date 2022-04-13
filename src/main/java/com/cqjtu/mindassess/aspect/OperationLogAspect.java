package com.cqjtu.mindassess.aspect;

import cn.dev33.satoken.stp.StpUtil;
import com.cqjtu.mindassess.annotation.LogOperation;
import com.cqjtu.mindassess.entity.OperationLog;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.service.IOperationLogService;
import com.cqjtu.mindassess.util.HttpContextUtils;
import com.cqjtu.mindassess.util.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @author 凡森
 * @date 2022/4/13
 */
@Aspect
@Component
@Slf4j
public class OperationLogAspect {

    @Autowired
    private IOperationLogService iOperationLogService;

    @Pointcut("@annotation(com.cqjtu.mindassess.annotation.LogOperation)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object proceed = point.proceed();
        long time = System.currentTimeMillis() - beginTime;
        saveOperationLog(point, time);
        return proceed;
    }

    private void saveOperationLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        OperationLog operationLog = new OperationLog();
        LogOperation logOperationAnnotation = method.getAnnotation(LogOperation.class);
        if (logOperationAnnotation != null) {
            operationLog.setOperation(logOperationAnnotation.value());
        }
        Object[] args = joinPoint.getArgs();
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            StringBuilder params = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                params.append("  ").append(paramNames[i]).append(": ").append(args[i]);
            }
            operationLog.setParams(params.toString());
        }
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        operationLog.setIp(IPUtils.getIpAddr(request));
        operationLog.setLink(request.getRequestURL().toString());
        Long userId = ((User) StpUtil.getSession().get("user")).getId();
        operationLog.setUserId(userId);
        operationLog.setExecTime((int) time);
        operationLog.setCreateTime(LocalDateTime.now());
        iOperationLogService.save(operationLog);
    }
}
