package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.ProductDao;
import com.ssm.pojo.Orders;
import com.ssm.pojo.Product;
import com.ssm.service.ProductSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 产品服务的实现类
 * @author kneesh
 * @date 2021/4/26-15:32
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductSerice {
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> queryAllProduct(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return productDao.queryAllProduct();
    }

    @Override
    public void addProduct(Product product) throws Exception {
        productDao.addProduct(product);
    }

    @Override
    public int updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    @Override
    public Product queryProductById(int id) {
        return productDao.queryProductById(id);
    }

    @Override
    public void deleteProductById(Integer id) {
        productDao.deleteProductById(id);
    }

    @Override
    public List<Orders> queryOrdersByProductId(Integer id) {
        return productDao.queryOrdersByProductId(id);
    }

    @Override
    public int checkPermission(String username, String url) {
        return productDao.checkPermission(username,url);
    }
}
