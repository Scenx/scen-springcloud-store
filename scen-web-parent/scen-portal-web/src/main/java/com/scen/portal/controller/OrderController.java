package com.scen.portal.controller;

import com.scen.pojo.CartItem;
import com.scen.pojo.Order;
import com.scen.pojo.TbUser;
import com.scen.portal.service.CartService;
import com.scen.portal.service.OrderService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 订单处理
 *
 * @author Scen
 * @date 2018/4/17 15:23
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    public CartService cartService;

    @Autowired
    private OrderService orderService;


    /**
     * 展示订单结算
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/order-cart")
    public String showOrderCart(HttpServletRequest request, HttpServletResponse response, Model model) {
//        取购物车商品列表
        List<CartItem> list = cartService.getCartItemList(request, response);
//        传递给页面
        model.addAttribute("cartList", list);
        return "order-cart";
    }

    /**
     * 创建订单
     *
     * @param order
     * @param model
     * @return
     */
    @RequestMapping("/create")
    public String createOrder(Order order, Model model, HttpServletRequest request) {
        try {
//            从req作用域中取用户信息
            TbUser user = (TbUser) request.getAttribute("user");
//            在order对象中补全用户信息
            order.setUserId(user.getId());
            order.setBuyerNick(user.getUsername());
//            调用服务
            String orderId = orderService.createOrder(order);
            model.addAttribute("orderId", orderId);
            model.addAttribute("payment", order.getPayment());
            model.addAttribute("data", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "创建订单出错，请稍后重试");
            return "error/exception";
        }
    }

}
