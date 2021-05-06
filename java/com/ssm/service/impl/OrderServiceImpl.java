package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.OrderDao;
import com.ssm.pojo.Orders;
import com.ssm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kneesh
 * @Description 订单服务的实现类
 * @date 2020/7/9-20:10
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Orders> queryAllOrders(int page,int size) {
        PageHelper.startPage(page,size);
        return orderDao.queryAllOrders();
    }

    @Override
    public Orders queryOrderById(int id) {
        return orderDao.queryOrderById(id);
    }

    @Override
    public void deleteOrderById(int id) {
        orderDao.deleteOrderById(id);
    }

    @Override
    public int checkPermission(String username, String url) {
        return orderDao.checkPermission(username,url);
    }
}
