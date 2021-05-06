package com.ssm.pojo;

import com.ssm.util.DateUtils;

import java.util.Date;
import java.util.List;

/**
 * @author kneesh
 * @Description 订单表
 * @date 2021/4/26-19:47
 */
public class Orders {
    private Integer id;
    private String orderNum;
    private Date orderTime;
    private String orderTimeStr;
    private Integer peopleCount;
    private String orderDesc;
    private Integer payType;
    private String payTypeStr;
    private Integer orderStatus;
    private Product product;
    private List<Traveller> travellers;
    private Member member;

    public Orders() {
    }

    public Orders(Integer id, String orderNum, Date orderTime, String orderTimeStr, Integer peopleCount, String orderDesc, Integer payType, String payTypeStr, Integer orderStatus, Product product, List<Traveller> travellers, Member member) {
        this.id = id;
        this.orderNum = orderNum;
        this.orderTime = orderTime;
        this.orderTimeStr = orderTimeStr;
        this.peopleCount = peopleCount;
        this.orderDesc = orderDesc;
        this.payType = payType;
        this.payTypeStr = payTypeStr;
        this.orderStatus = orderStatus;
        this.product = product;
        this.travellers = travellers;
        this.member = member;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderTimeStr() {
        if (orderTime != null) {
            orderTimeStr = DateUtils.dateToString(orderTime, "yyyy-MM-dd HH:mm:ss");
        }
        return orderTimeStr;
    }

    public void setOrderTimeStr(String orderTimeStr) {
        this.orderTimeStr = orderTimeStr;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayTypeStr() {
        return payTypeStr;
    }

    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Traveller> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<Traveller> travellers) {
        this.travellers = travellers;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
