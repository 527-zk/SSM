package com.ssm.pojo;

/**
 * 响应信息的实体类
 * @author kneesh
 * @date 2021/4/27-21:47
 */
public class Message {
    public String message;
    public int status;

    public Message(){}
    public Message(int code,String msg){
        this.message=msg;
        this.status=code;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
