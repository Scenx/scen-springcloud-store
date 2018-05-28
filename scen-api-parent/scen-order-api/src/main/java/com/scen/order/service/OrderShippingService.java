package com.scen.order.service;

import com.scen.order.service.hystrix.OrderShippingServiceHystrix;
import com.scen.pojo.OrderShipping;
import com.scen.vo.ScenResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 订单物流信息接口
 * @author Scen
 * @date 2018/5/28 14:48
 */
@FeignClient(value = "scen-order-service", fallback = OrderShippingServiceHystrix.class)
public interface OrderShippingService {

    /**
     * 创建订单物流信息
     * @param orderId
     * @param orderShipping
     * @throws Exception
     * @return
     */
    @RequestMapping("/orderShippingService/createOrderShipping")
    ScenResult createOrderShipping(
           @RequestParam("orderId") String orderId,
            OrderShipping orderShipping
    ) throws Exception;
}
