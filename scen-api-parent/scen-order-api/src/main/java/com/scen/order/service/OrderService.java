package com.scen.order.service;

import com.scen.order.service.hystrix.OrderServiceHystrix;
import com.scen.pojo.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 订单接口
 * @author Scen
 * @date 2018/5/28 14:48
 */
@FeignClient(value = "scen-order-service", fallback = OrderServiceHystrix.class)
public interface OrderService {

    /**
     * 创建订单
     * @param order
     * @throws Exception
     * @return
     */
    @RequestMapping("orderService/createOrder")
    String createOrder(Order order) throws Exception;
}
