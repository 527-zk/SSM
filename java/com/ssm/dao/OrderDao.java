package com.ssm.dao;

import com.ssm.pojo.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author kneesh
 * @Description 订单服务的DAO层
 * @date 2021/4/26-14:59
 */
public interface OrderDao {
    /**
     * 查询所有的订单
     * @return
     */
    List<Orders> queryAllOrders();

    /**
     * 根据ID查询订单
     * @param id
     * @return
     */
    Orders queryOrderById(@Param("id") int id);

    /**
     * 根据ID删除订单
     * @param id
     */
    void deleteOrderById(@Param("id") int id);

    /**
     * 检查当前登录用户是否有权限
     * @param username
     * @param url
     * @return
     */
    int checkPermission(@Param("username") String username, @Param("url") String url);
}
