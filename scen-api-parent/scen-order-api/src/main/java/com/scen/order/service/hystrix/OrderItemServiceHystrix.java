package com.scen.order.service.hystrix;

import com.scen.order.service.OrderItemService;
import com.scen.pojo.OrderItem;
import com.scen.vo.ScenResult;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 订单明细熔断器
 * @author Scen
 * @date 2018/5/28 14:51
 */
@Component
public class OrderItemServiceHystrix implements OrderItemService {
    @Override
    public ScenResult createOrderItem(String orderItemId,List<OrderItem> orderItemList) throws Exception {
        return ScenResult.build(233, "服务不可用");
    }
}
