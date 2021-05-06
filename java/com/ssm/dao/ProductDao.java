package com.ssm.dao;

import com.ssm.pojo.Orders;
import com.ssm.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * 产品服务的DAO层
 * @author kneesh
 * @date 2021/4/26-15:33
 */
public interface ProductDao {
    /**
     * 查询所有的书籍并返回
     * @return
     * @throws Exception
     */
    List<Product> queryAllProduct() throws Exception;

    /**
     * 添加产品
     * @param product
     */
    void addProduct(Product product);

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
    Product queryProductById(@Param("id") int id);

    /**
     * 根据产品ID删除产品
     * @param id
     */
    void deleteProductById(@Param("id") Integer id);

    /**
     * 根据产品ID查询其下订单
     * @param id
     * @return
     */
    List<Orders> queryOrdersByProductId(@Param("id") Integer id);

    int checkPermission(@Param("username") String username, @Param("url") String url);
}
