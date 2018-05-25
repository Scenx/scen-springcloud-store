package com.scen.search.service.impl;

import com.scen.dao.ItemDao;
import com.scen.search.service.SyncItemService;
import com.scen.vo.SearchItem;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


/**
 * 商品同步服务
 *
 * @author Scen
 * @date 2018/5/25 14:00
 */
@RestController
public class SyncItemServiceImpl implements SyncItemService {

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private ItemDao itemDao;

    @Override
    @RabbitListener(queues = "addItem")
    @RabbitHandler
    public void syncAddItem(Long itemId) {
        //        根据id查询数据库商品取出索引库所需字段
        SearchItem item = itemDao.getItem(itemId);
        if (item != null) {
            SolrInputDocument document = new SolrInputDocument();
            document.setField("id", item.getId());
            document.setField("item_title", item.getTitle());
            document.setField("item_sell_point", item.getSellPoint());
            document.setField("item_price", item.getPrice());
            document.setField("item_image", item.getImage());
            document.setField("item_category_name", item.getCategoryName());
            document.setField("item_desc", item.getItemDesc());
            try {
                solrClient.add(document);
                solrClient.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @RabbitListener(queues = "delItem")
    @RabbitHandler
    public void syncDelItem(Long itemId) {
        try {
            solrClient.deleteById(itemId + "");
            solrClient.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
