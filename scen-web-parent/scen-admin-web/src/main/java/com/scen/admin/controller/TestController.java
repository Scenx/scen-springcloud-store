package com.scen.admin.controller;

import com.scen.item.service.ItemCatService;
import com.scen.item.service.ItemDescService;
import com.scen.item.service.ItemParamItemService;
import com.scen.item.service.ItemParamService;
import com.scen.pojo.ItemParam;
import com.scen.vo.EUDdataGridResult;
import com.scen.vo.EUTreeNode;
import com.scen.vo.ScenResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author Scen
 * @date 2018/5/12 10:58
 */
@RestController
public class TestController {

    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/test")
    public ScenResult test() throws Exception {
        return itemParamService.deleteItemParam(new Long[]{29L});
    }
}
