package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.pojo.Orders;
import com.ssm.pojo.UserInfo;
import com.ssm.service.OrderService;
import com.ssm.service.UserService;
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
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;
import org.springframework.web.servlet.view.InternalResourceView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author kneesh
 * @Description 订单服务的Controller
 * @date 2021/4/26-14:52
 */
@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/queryAllOrders.do")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_MANAGER','ROLE_GROUP')")
    public ModelAndView queryAllOrders(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                       @RequestParam(name = "size",required = true,defaultValue = "10") Integer size){
        ModelAndView mv = new ModelAndView();
        List<Orders> orders = orderService.queryAllOrders(page,size);
        PageInfo pageInfo = new PageInfo(orders);
        mv.addObject("ordersList",pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }

    @RequestMapping("/queryOrderById.do")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_MANAGER','ROLE_GROUP')")
    public ModelAndView queryOrderById(@RequestParam(name = "id",required = true) Integer id){
        ModelAndView mv = new ModelAndView();
        Orders orders = orderService.queryOrderById(id);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }

    @RequestMapping("/deleteOrderById.do")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_MANAGER')")
    public String deleteOrderById(@RequestParam(name = "id",required = true) Integer id){
        orderService.deleteOrderById(id);
        return "redirect:queryAllOrders.do";
    }
}
