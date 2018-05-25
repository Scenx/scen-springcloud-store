package com.scen.search.service.impl;

import com.scen.common.utils.ExceptionUtil;
import com.scen.dao.ItemDao;
import com.scen.search.service.SearchItemService;
import com.scen.vo.ScenResult;
import com.scen.vo.SearchItem;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Scen
 * @date 2018/5/25 15:53
 */
@RestController
public class SearchItemServiceImpl implements SearchItemService {

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private ItemDao itemDao;


    @Override
    public ScenResult importAllItems() {
        try {
//        查询商品列表
            List<SearchItem> list = itemDao.getItemList();
//        把信息写入索引库
            for (SearchItem item : list) {
                //            创建一个SolrInputDocument对象
                SolrInputDocument document = new SolrInputDocument();
                document.setField("id", item.getId());
                document.setField("item_title", item.getTitle());
                document.setField("item_sell_point", item.getSellPoint());
                document.setField("item_price", item.getPrice());
                document.setField("item_image", item.getImage());
                document.setField("item_category_name", item.getCategoryName());
                document.setField("item_desc", item.getItemDesc());
                solrClient.add(document);
            }
//        提交修改
            solrClient.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return ScenResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return ScenResult.ok();
    }
}
