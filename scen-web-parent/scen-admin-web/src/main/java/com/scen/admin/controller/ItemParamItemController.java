package com.scen.admin.controller;

import com.scen.item.service.ItemParamItemService;
import com.scen.vo.ScenResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 展示商品规格参数表现层
 *
 * @author Scen
 * @date 2018/3/22 14:00
 */
@RestController
@RequestMapping("/item/param/item")
public class ItemParamItemController {

    @Autowired
    private ItemParamItemService itemParamItemService;

    /**
     * 根据查询商品具体规格参数
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/query/{itemId}")
    public ScenResult getParamData(@PathVariable Long itemId) {
        return itemParamItemService.getItemParamByItemId(itemId);
    }

}
