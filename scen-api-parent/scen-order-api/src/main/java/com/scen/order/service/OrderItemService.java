package com.scen.order.service;

import com.scen.order.service.hystrix.OrderItemServiceHystrix;
import com.scen.pojo.OrderItem;
import com.scen.vo.ScenResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 订单明细接口
 * @author Scen
 * @date 2018/5/28 14:48
 */
@FeignClient(value = "scen-order-service", fallback = OrderItemServiceHystrix.class)
public interface OrderItemService {

    /**
     * 创建订单明细
     * @param orderItemList
     * @param orderId
     * @throws Exception
     * @return
     */
    @RequestMapping("/orderItemService/createOrderItem")
    ScenResult createOrderItem(
            @RequestParam("orderId") String orderId,
            List<OrderItem> orderItemList
    ) throws Exception;
}
