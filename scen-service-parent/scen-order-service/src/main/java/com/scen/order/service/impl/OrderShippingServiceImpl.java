package com.scen.order.service.impl;

import com.scen.dao.OrderShippingDao;
import com.scen.order.service.OrderShippingService;
import com.scen.pojo.OrderShipping;
import com.scen.vo.ScenResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 订单物流信息服务
 * @author Scen
 * @date 2018/5/28 16:00
 */
@RestController
public class OrderShippingServiceImpl implements OrderShippingService {


    @Autowired
    private OrderShippingDao orderShippingDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ScenResult createOrderShipping(
            String orderId,
            @RequestBody OrderShipping orderShipping
    ) throws Exception {
        //        插入物流表
//        补全物流表的属性
        Date date = new Date();
        orderShipping.setOrderId(orderId);
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
        orderShippingDao.insert(orderShipping);
        return ScenResult.ok();
    }
}
