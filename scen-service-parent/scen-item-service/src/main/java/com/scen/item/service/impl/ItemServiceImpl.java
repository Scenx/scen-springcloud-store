package com.scen.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scen.common.utils.IDUtils;
import com.scen.dao.ItemDao;
import com.scen.dao.ItemDescDao;
import com.scen.dao.ItemParamItemDao;
import com.scen.item.service.ItemService;
import com.scen.pojo.Item;
import com.scen.pojo.ItemDesc;
import com.scen.pojo.ItemParamItem;
import com.scen.vo.EUDdataGridResult;
import com.scen.vo.ItemBean;
import com.scen.vo.ScenResult;
import com.scen.vo.SolrIf;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品服务
 *
 * @author Scen
 * @date 2018/5/25 0:12
 */
@RestController
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ItemDescDao itemDescDao;

    @Autowired
    private ItemParamItemDao itemParamItemDao;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.syncItem.exchange}")
    private String SYNC_ITEM_EXCHANGE;

    @Value("${spring.rabbitmq.addItem.routing-key}")
    private String SYNC_ADD_ITEM_ROUTING_KEY;

    @Value("${spring.rabbitmq.delItem.routing-key}")
    private String SYNC_DEL_ITEM_ROUTING_KEY;

    @Override
    public Item getItemById(Long itemId) {
        return itemDao.selectByPrimaryKey(itemId);
    }

    @Override
    public EUDdataGridResult getItemList(Integer page, Integer rows, Long id, String title, String catName, Long startPrice, Long endPrice) {
        Map<String, Object> map = new HashMap<String, Object>();
        //检索条件封装
        if (id != null) {
            map.put("id", id);
        }
        if (StringUtils.isNotBlank(title)) {
            map.put("title", title);
        }
        if (StringUtils.isNotBlank(catName)) {
            map.put("catName", catName);
        }
        if (startPrice != null && endPrice == null) {
            map.put("startPrice", startPrice);
        }
        if (endPrice != null && startPrice == null) {
            map.put("endPrice", endPrice);
        }
        if (startPrice != null && endPrice != null) {
            map.put("startPrice", startPrice);
            map.put("endPrice", endPrice);
        }

//        查询商品列表
//        分页处理
        PageHelper.startPage(page, rows);
        List<ItemBean> list = itemDao.getSearchItemList(map);
//        创建一个返回值对象
        EUDdataGridResult result = new EUDdataGridResult();
        result.setRows(list);
//        取记录总条数
        PageInfo<ItemBean> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ScenResult createItem(
            @RequestBody Item item,
            @RequestBody ItemDesc itemDesc,
            @RequestBody ItemParamItem itemParamItem
    ) throws Exception {
//        补全item
//        生成商品ID
        long itemId = IDUtils.genItemId();
        item.setId(itemId);
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());

//        插入到数据库
        itemDao.insert(item);
        ScenResult result = insertItemDesc(itemId, itemDesc);
        if (result.getStatus() != 200) {
            throw new Exception();
        }
        result = insertItemParamItem(itemId, itemParamItem);
        if (result.getStatus() != 200) {
            throw new Exception();
        }
//       同步商品到检索服务器
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                rabbitTemplate.convertAndSend(SYNC_ITEM_EXCHANGE, SYNC_ADD_ITEM_ROUTING_KEY, item.getId());
            }
        });
        return ScenResult.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ScenResult updateItem(
            @RequestBody Item item,
            @RequestBody ItemDesc itemDesc,
            Long itemParamId,
            String itemParams
    ) throws Exception {
        item.setUpdated(new Date());
        itemDao.updateByPrimaryKey(item);
        ScenResult result = updateItemDesc(item.getId(), itemDesc);
        if (result.getStatus() != 200) {
            throw new Exception();
        }
        result = updateItemParamItem(item.getCreated(), item.getId(), itemParamId, itemParams);
        if (result.getStatus() != 200) {
            throw new Exception();
        }

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                if (item.getStatus() == 1) {
//                  同步商品到检索服务器
                    rabbitTemplate.convertAndSend(SYNC_ITEM_EXCHANGE, SYNC_ADD_ITEM_ROUTING_KEY, item.getId());
                }
            }
        });
        return ScenResult.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ScenResult deleteItem(@RequestBody List<SolrIf> list) throws Exception {
        for (SolrIf solrIf : list) {
            itemDao.deleteByPrimaryKey(solrIf.getId());
        }
        for (SolrIf solrIf : list) {
            if (solrIf.getStatus() == 1) {
                //        同步商品到检索服务器
                rabbitTemplate.convertAndSend(SYNC_ITEM_EXCHANGE, SYNC_DEL_ITEM_ROUTING_KEY, solrIf.getId());
            }
        }
        return ScenResult.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ScenResult instockItem(@RequestBody List<SolrIf> list) throws Exception {
        Item item = new Item();
        for (SolrIf solrIf : list) {
            item.setId(solrIf.getId());
            item.setStatus((byte) 2);
            itemDao.updateByPrimaryKeySelective(item);
            if (solrIf.getStatus() == 1) {
                //        同步商品到检索服务器
                rabbitTemplate.convertAndSend(SYNC_ITEM_EXCHANGE, SYNC_DEL_ITEM_ROUTING_KEY, solrIf.getId());
            }
        }
        return ScenResult.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ScenResult reshelfItem(@RequestBody Long[] ids) throws Exception {
        Item item = new Item();
        for (Long id : ids) {
            item.setId(id);
            item.setStatus((byte) 1);
            itemDao.updateByPrimaryKeySelective(item);
//            同步到检索服务器
            rabbitTemplate.convertAndSend(SYNC_ITEM_EXCHANGE, SYNC_ADD_ITEM_ROUTING_KEY, id);
        }
        return ScenResult.ok();
    }


    /**
     * 添加商品描述
     *
     * @param itemDesc
     * @return
     */
    private ScenResult insertItemDesc(Long itemId, ItemDesc itemDesc) {
        itemDesc.setItemId(itemId);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDescDao.insert(itemDesc);
        return ScenResult.ok();
    }


    /**
     * 添加商品规格的具体参数
     *
     * @param itemId
     * @param itemParamItem
     * @return
     */
    private ScenResult insertItemParamItem(Long itemId, ItemParamItem itemParamItem) {
        itemParamItem.setItemId(itemId);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        itemParamItemDao.insert(itemParamItem);
        return ScenResult.ok();
    }


    /**
     * 更新商品描述
     *
     * @param itemId
     * @param itemDesc
     * @return
     */
    private ScenResult updateItemDesc(Long itemId, ItemDesc itemDesc) {
        itemDesc.setItemId(itemId);
        itemDesc.setUpdated(new Date());
        itemDescDao.updateByPrimaryKey(itemDesc);
        return ScenResult.ok();
    }


    /**
     * 更新商品规格参数
     *
     * @param itemId
     * @param id
     * @param paramData
     * @return
     */
    private ScenResult updateItemParamItem(Date created, Long itemId, Long id, String paramData) {
        ItemParamItem itemParamItem = new ItemParamItem();
        itemParamItem.setId(id);
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(paramData);
        itemParamItem.setCreated(created);
        itemParamItem.setUpdated(new Date());
        itemParamItemDao.updateByPrimaryKey(itemParamItem);
        return ScenResult.ok();
    }
}
