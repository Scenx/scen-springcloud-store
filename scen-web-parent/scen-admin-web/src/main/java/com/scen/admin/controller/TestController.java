package com.scen.admin.controller;

import com.scen.item.service.*;
import com.scen.pojo.Item;
import com.scen.pojo.ItemDesc;
import com.scen.pojo.ItemParam;
import com.scen.pojo.ItemParamItem;
import com.scen.vo.EUDdataGridResult;
import com.scen.vo.EUTreeNode;
import com.scen.vo.ScenResult;
import com.scen.vo.SolrIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Scen
 * @date 2018/5/12 10:58
 */
@RestController
public class TestController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/test")
    public ScenResult test() throws Exception {
        List<SolrIf> list = new ArrayList<>();
        SolrIf solrIf = new SolrIf();
        solrIf.setId(152724575381884L);
        solrIf.setStatus(1);
        list.add(solrIf);
        return itemService.deleteItem(list);
    }
}
