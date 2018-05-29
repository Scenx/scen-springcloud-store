package com.scen.portal.controller;


import com.scen.common.utils.CookieUtils;
import com.scen.common.utils.JsonUtils;
import com.scen.order.service.OrderItemService;
import com.scen.order.service.OrderService;
import com.scen.order.service.OrderShippingService;
import com.scen.pojo.User;
import com.scen.vo.CartItem;
import com.scen.vo.PortalOrder;
import com.scen.vo.ScenResult;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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

    @Value("${SCEN_CART_NAME}")
    private String SCEN_CART_NAME;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderShippingService orderShippingService;


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
        List<CartItem> itemList = null;
        //        从cookie中取商品列表
        String cartJson = CookieUtils.getCookieValue(request, SCEN_CART_NAME, true);
        if (StringUtils.isBlank(cartJson)) {
            itemList = new ArrayList<>();
        } else {
            //        把json转换成商品列表
            itemList = JsonUtils.jsonToList(cartJson, CartItem.class);
        }
//        传递给页面
        model.addAttribute("cartList", itemList);
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
    public String createOrder(PortalOrder order, Model model, HttpServletRequest request) {
        try {
//            从req作用域中取用户信息
            User user = (User) request.getAttribute("user");
//            在order对象中补全用户信息
            order.setUserId(user.getId());
            order.setBuyerNick(user.getUsername());
//            调用服务
//            创建订单
            model.addAttribute("message", "创建订单出错，请稍后重试");
            String orderId = orderService.createOrder(order);
            if (StringUtils.isBlank(orderId)) {
                return "error/exception";
            }
            ScenResult result = orderItemService.createOrderItem(orderId, order.getOrderItems());
            if (result.getStatus() != 200) {
                return "error/exception";
            }
            result = orderShippingService.createOrderShipping(orderId, order.getOrderShipping());
            if (result.getStatus() != 200) {
                return "error/exception";
            }
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
