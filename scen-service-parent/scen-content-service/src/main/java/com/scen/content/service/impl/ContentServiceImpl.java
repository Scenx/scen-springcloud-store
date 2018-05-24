package com.scen.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scen.content.service.ContentService;
import com.scen.dao.ContentDao;
import com.scen.pojo.Content;
import com.scen.vo.EUDdataGridResult;
import com.scen.vo.ScenResult;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;


import java.util.Date;
import java.util.List;

/**
 * 内容服务提供者
 *
 * @author Scen
 * @date 2018/5/12 8:59
 */
@RestController
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentDao contentDao;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.syncContent.exchange}")
    private String SYNC_CONTENT_EXCHANGE;

    @Value("${spring.rabbitmq.syncContent.routing-key}")
    private String SYNC_CONTENT_ROUTING_KEY;

    @Override
    public EUDdataGridResult getContentList(Integer page, Integer rows, Long categoryId) {
        //        查询商品列表
//        分页处理
        PageHelper.startPage(page, rows);
        Example example = new Example(Content.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryId", categoryId);
        List<Content> list = contentDao.selectByExample(example);
//        创建一个返回值对象
        EUDdataGridResult result = new EUDdataGridResult();
        result.setRows(list);
//        取记录总条数
        PageInfo<Content> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ScenResult saveContent(@RequestBody Content content) throws Exception {
        //        补全内容
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentDao.insert(content);
//        添加缓存同步逻辑
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                rabbitTemplate.convertAndSend(SYNC_CONTENT_EXCHANGE, SYNC_CONTENT_ROUTING_KEY, content.getCategoryId());
            }
        });
        return ScenResult.ok();
    }

    @Override
    public ScenResult deleteContent(@RequestBody Long[] ids) throws Exception {
        for (Long id : ids) {
            Content content = contentDao.selectByPrimaryKey(id);
            //        添加缓存同步逻辑
            rabbitTemplate.convertAndSend(SYNC_CONTENT_EXCHANGE, SYNC_CONTENT_ROUTING_KEY, content.getCategoryId());
            contentDao.deleteByPrimaryKey(id);
        }
        return ScenResult.ok();
    }

    @Override
    public ScenResult editContent(@RequestBody Content content) {
        content.setUpdated(new Date());
        contentDao.updateByPrimaryKeySelective(content);
        //        添加缓存同步逻辑
        rabbitTemplate.convertAndSend(SYNC_CONTENT_EXCHANGE, SYNC_CONTENT_ROUTING_KEY, content.getCategoryId());
        return ScenResult.ok();
    }
}
