package com.ssm.controller;

import com.ssm.pojo.SysLog;
import com.ssm.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 *
 * @author kneesh
 * @Description 日志AOP
 * @date 2021/4/28-16:32
 */
@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private SysLogService sysLogService;

    private Date visitTime;
    private Class clazz;
    private Method method;


    /**
     * 前置通知，拦截Controller下的所有类的所有方法
     * 主要获取开始时间，执行的类是哪一个，执行哪个方法
     * @param joinPoint
     */
    @Before("execution(* com.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        // 当前时间就是开始时间
        visitTime=new Date();
        // 获取当前的类
        clazz = joinPoint.getTarget().getClass();
        // 获取当前执行方法的名字
        String methodName = joinPoint.getSignature().getName();
        // 获取访问方法的参数
        Object[] args = joinPoint.getArgs();
        if(args==null|| args.length==0) {
            // 查找无参的方法
            method = clazz.getMethod(methodName);
        }else{
            // 查找有参的方法
            Class[] classes= new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classes[i]=args[i].getClass();
            }
            method = clazz.getMethod(methodName,classes);
        }
    }

    /**
     * 后置通知，拦截Controller下的所有类的所有方法
     * @param joinPoint
     */
    @After("execution(* com.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint joinPoint){
        // 获取访问的时长
        long time = System.currentTimeMillis()-visitTime.getTime();
        // 获取Url
        String url="";
        if(clazz!=null&&method!=null&&clazz!=LogAop.class){
            // 1.获取类上的注解
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(classAnnotation!=null){
                String[] classValue = classAnnotation.value();
                // 获取方法上的注解
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation!=null){
                    String[] methodValue = methodAnnotation.value();
                    url=classValue[0]+methodValue[0];
                }
            }
        }
        // 获取访问的IP
        String ip = httpServletRequest.getRemoteAddr();
        // 获取当前操作的用户
        // 注意未登录状态下的空指针异常
        SecurityContext context = SecurityContextHolder.getContext();
        User user;
        String username;
        //忘记密码时修改密码
        //未登录状态下Authentication为null,无法获取principal
        //只记录用户名为annoumous
        if(context.getAuthentication()==null){
            username="annoumous";
        }
        else{
            user = (User) context.getAuthentication().getPrincipal();
            username = user.getUsername();
        }

        // 将日志相关信息封装到syslog对象
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(time);
        sysLog.setIp(ip);
        sysLog.setUrl(url);
        sysLog.setVisitTime(visitTime);
        sysLog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());
        sysLog.setUsername(username);
        // 保存到数据库
        sysLogService.saveLog(sysLog);
    }
}
