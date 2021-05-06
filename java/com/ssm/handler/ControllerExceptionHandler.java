package com.ssm.handler;

import com.ssm.pojo.Message;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller层的Advice
 * @author Admin
 * @date 2021/4/28-10:38
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    private final Logger logger = Logger.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Message handler(Exception e){
        //if (e instanceof xxxException)
        return new Message(403,"权限不足");
    }
}
