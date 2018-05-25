package com.scen.admin.controller;


import com.scen.item.service.ItemCatService;
import com.scen.vo.EUTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品分类管理表现层
 *
 * @author Scen
 * @date 2018/3/10 12:42
 */
@RestController
@RequestMapping("/item/cat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    /**
     * 取所有商品分类
     *
     * @param patentId
     * @return
     */
    @RequestMapping("/list")
    private List<EUTreeNode> getCatList(@RequestParam(value = "id", defaultValue = "0") Long patentId) {
        return itemCatService.getCatList(patentId);
    }
}
