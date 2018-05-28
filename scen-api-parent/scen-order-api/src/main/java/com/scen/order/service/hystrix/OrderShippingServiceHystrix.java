package com.scen.order.service.hystrix;

import com.scen.order.service.OrderShippingService;
import com.scen.pojo.OrderShipping;
import com.scen.vo.ScenResult;
import org.springframework.stereotype.Component;

/**
 * 订单物流信息熔断器
 * @author Scen
 * @date 2018/5/28 14:53
 */
@Component
public class OrderShippingServiceHystrix implements OrderShippingService {
    @Override
    public ScenResult createOrderShipping(String orderId, OrderShipping orderShipping) throws Exception {
        return ScenResult.build(233, "服务不可用");
    }
}
