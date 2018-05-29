package com.scen.search.service.impl;

import com.scen.common.utils.ExceptionUtil;
import com.scen.dao.ItemDao;
import com.scen.search.service.SearchItemService;
import com.scen.vo.ScenResult;
import com.scen.vo.SearchItem;
import com.scen.vo.SearchResult;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Override
    public SearchResult getPortalItems(String queryString, Integer page, Integer rows) throws Exception {
        //        创建查询条件
        SolrQuery query = new SolrQuery();
//        设置查询条件
        query.setQuery(queryString);
//        设置分页
        query.setStart((page - 1) * rows);
        query.setRows(rows);
//        设置默认搜索域
        query.set("df", "item_keywords");
//        设置高亮显示
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");
//        执行查询
        //        接受查询结果result
        SearchResult result = new SearchResult();
//       根据查询条件查询索引库
        QueryResponse queryResponse = solrClient.query(query);
//        取查询结果
        SolrDocumentList solrDocumentList = queryResponse.getResults();
//        取总数
        result.setRecordCount(solrDocumentList.getNumFound());
//        商品列表
        List<SearchItem> itemList = new ArrayList<>();
//        取高亮
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
//      取商品列表
        for (SolrDocument solrDocument : solrDocumentList) {
            SearchItem item = new SearchItem();
            item.setId((String) solrDocument.get("id"));
//            取高亮显示的结果
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String title = "";
            if (list != null && list.size() > 0) {
                title = list.get(0);
            } else {
                title = (String) solrDocument.get("item_title");
            }
            item.setTitle(title);
            item.setImage((String) solrDocument.get("item_image"));
            item.setPrice((Long) solrDocument.get("item_price"));
            item.setSellPoint((String) solrDocument.get("item_sell_point"));
            item.setCategoryName((String) solrDocument.get("item_category_name"));
            itemList.add(item);
        }
        result.setItemList(itemList);
//        计算查询结果总页数
        Long recordCount = result.getRecordCount();
        Long pageCount = recordCount / rows;
        if (recordCount % rows > 0) {
            pageCount++;
        }
        result.setPageCount(pageCount);
        result.setCurPage((long) page);
        return result;
    }
}
