package com.ssm.service;


import com.ssm.pojo.Orders;
import com.ssm.pojo.Product;

import java.util.List;

/**
 * @author kneesh
 * @desc 产品服务的接口
 * @date 2021/4/26-15:30
 */
public interface ProductSerice {

    /**
     * 查询所有的书籍并返回
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    List<Product> queryAllProduct(int page, int size) throws Exception;

    /**
     * 添加产品
     * @param product
     * @throws Exception
     */
    void addProduct(Product product) throws Exception;


    /**
     * 更新产品
     * @param product
     * @return
     */
    int updateProduct(Product product);

    /**
     * 根据产品ID查询产品
     * @param id
     * @return
     */
    Product queryProductById(int id);

    /**
     * 依据产品ID删除产品
     * @param id
     */
    void deleteProductById(Integer id);

    /**
     * 依据产品ID查询该产品的订单
     * @param id
     * @return
     */
    List<Orders> queryOrdersByProductId(Integer id);

    /**
     * 检查用户权限
     * @param username
     * @param url
     * @return
     */
    int checkPermission(String username, String url);
}
