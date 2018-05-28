package com.scen.order.service.hystrix;

import com.scen.order.service.OrderService;
import com.scen.pojo.Order;
import org.springframework.stereotype.Component;

/**
 * 订单熔断器
 * @author Scen
 * @date 2018/5/28 14:52
 */
@Component
public class OrderServiceHystrix implements OrderService {
    @Override
    public String createOrder(Order order) throws Exception {
        return null;
    }
}
