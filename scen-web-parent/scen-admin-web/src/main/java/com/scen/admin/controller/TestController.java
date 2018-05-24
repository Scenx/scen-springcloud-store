package com.scen.admin.controller;

import com.scen.admin.service.AdminUserService;
import com.scen.item.service.ItemCatService;
import com.scen.vo.EUTreeNode;
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
    private ItemCatService itemCatService;

    @RequestMapping("/test")
    public List<EUTreeNode> test() throws Exception {
        return itemCatService.getCatList(30L);
    }
}
