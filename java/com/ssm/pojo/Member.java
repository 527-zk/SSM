package com.ssm.pojo;

/**
 * @author kneesh
 * @Description 会员表
 * @date 2021/4/26-19:48
 */
public class Member {
    private Integer id;
    private String name;
    private String nickName;
    private String phoneNum;
    private String email;

    public Member() {
    }

    public Member(Integer id, String name, String nickName, String phoneNum, String email) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
