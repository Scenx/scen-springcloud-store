package com.scen.vo;

import com.scen.pojo.Order;
import com.scen.pojo.OrderItem;
import com.scen.pojo.OrderShipping;

import java.util.List;

/**
 * 订单pojo
 *
 * @author Scen
 * @date 2018/4/17 14:20
 */
public class PortalOrder extends Order {

    private List<OrderItem> orderItems;

    private OrderShipping orderShipping;

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(OrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
