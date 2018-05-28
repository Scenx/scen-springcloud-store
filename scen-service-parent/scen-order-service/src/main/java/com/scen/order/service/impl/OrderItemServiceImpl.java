package com.scen.order.service.impl;

import com.scen.common.utils.SnowflakeIdWorker;
import com.scen.dao.OrderItemDao;
import com.scen.order.service.OrderItemService;
import com.scen.pojo.OrderItem;
import com.scen.vo.ScenResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 订单明细服务
 * @author Scen
 * @date 2018/5/28 15:51
 */
@RestController
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private SnowflakeIdWorker idWorker;

    @Autowired
    private OrderItemDao orderItemDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ScenResult createOrderItem(String orderId,@RequestBody List<OrderItem> orderItemList) {
        //        插入订单明细
        for (OrderItem OrderItem : orderItemList) {
//            补全订单明细
//            取订单明细id
            Long orderDetailId = idWorker.nextId();
            OrderItem.setId(orderDetailId + "");
            OrderItem.setOrderId(orderId);
//            向订单明细中插入记录
            orderItemDao.insert(OrderItem);
        }
        return ScenResult.ok();
    }
}
