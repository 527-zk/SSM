package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.pojo.Orders;
import com.ssm.pojo.Product;
import com.ssm.service.ProductSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author kneesh
 * @desc 产品服务的controller
 * @date 2024/4/26-15:19
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductSerice productService;

    /**
     * 查询全部产品
     * @return
     * @throws Exception
     */
    @RequestMapping("/queryAllProduct.do")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_GROUP')")
    public ModelAndView queryAllProduct(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "10") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.queryAllProduct(page,size);
        PageInfo pageInfo = new PageInfo(products);
        mv.addObject("productList", pageInfo);
        mv.setViewName("product-list");
        return mv;
    }

    /**
     * 添加产品
     * @param product
     * @return
     * @throws Exception
     */
    @RequestMapping("/addProduct.do")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    public String addProduct(Product product) throws Exception {
        // 判断是是新增还是编辑
        if(product.getId()!=null&& product.getId()>0){
            productService.updateProduct(product);
        }else{
            productService.addProduct(product);
        }
        return "redirect:queryAllProduct.do";
    }

    /**
     * 编辑产品信息
     * @param id
     * @return
     */
    @RequestMapping("/updateProductById.do")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    public ModelAndView updateProductById(Integer id){
        ModelAndView mv = new ModelAndView();
        Product product = productService.queryProductById(id);
        mv.addObject("product",product);
        mv.setViewName("product-add");
        return mv;
    }

    /**
     * 查看产品下的订单
     * @param id
     * @return
     */
    @RequestMapping("/queryOrdersByProductId.do")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_GROUP')")
    public ModelAndView queryOrdersByProductId(Integer id){
        ModelAndView mv = new ModelAndView();
        List<Orders> orders = productService.queryOrdersByProductId(id);
        mv.addObject("ordersList",orders);
        mv.setViewName("product-order-show");
        return mv;
    }

    /**
     * 根据产品ID删除产品
     * @param id
     * @return
     */
    @RequestMapping("/deleteProductById.do")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    public String deleteProductById(Integer id){
//        //  通过spring-security提供的UserDetails获取当前登录的用户名
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String userName = userDetails.getUsername();
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String url = request.getRequestURL().toString();

        productService.deleteProductById(id);
        return "redirect:queryAllProduct.do";
    }

}
