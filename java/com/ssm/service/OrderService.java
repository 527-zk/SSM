package com.ssm.service;

import com.ssm.pojo.Orders;

import java.util.List;

/**
 * @author kneesh
 * @Description 订单服务的接口
 * @date 2021/4/26-14:55
 */
public interface OrderService {
    /**
     * 查询所有的订单
     * @param page
     * @param size
     * @return
     */
    List<Orders> queryAllOrders(int page, int size);

    /**
     * 根据ID查询订单
     * @param id
     * @return
     */
    Orders queryOrderById(int id);

    /**
     * 依据ID删除订单
     * @param id
     */
    void deleteOrderById(int id);

    /**
     * 查看此用户是否有权限
     * @param username
     * @param url
     * @return
     */
    int checkPermission(String username, String url);
}
