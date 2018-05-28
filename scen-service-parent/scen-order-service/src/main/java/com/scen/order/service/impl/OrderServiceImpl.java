package com.scen.order.service.impl;

import com.scen.common.utils.SnowflakeIdWorker;
import com.scen.dao.OrderDao;
import com.scen.order.service.OrderService;
import com.scen.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 订单服务
 * @author Scen
 * @date 2018/5/28 15:14
 */
@RestController
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private SnowflakeIdWorker idWorker;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createOrder(@RequestBody Order order) throws Exception{
        String orderId = idWorker.nextId()+"";
        //        补全pojo属性
        Date date = new Date();
        order.setOrderId(orderId);
        order.setCreateTime(date);
        order.setUpdateTime(date);
//        状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        order.setStatus(1);
//        0未评价1已经评价
        order.setBuyerRate(0);
//        向订单表中插入数据
        orderDao.insert(order);
        return orderId;
    }
}
