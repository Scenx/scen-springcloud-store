package com.scen.portal.controller;

import com.scen.cache.service.ItemCacheService;
import com.scen.cache.service.ItemDescCacheService;
import com.scen.cache.service.ItemParamItemCacheService;
import com.scen.item.service.ItemDescService;
import com.scen.item.service.ItemParamItemService;
import com.scen.item.service.ItemService;
import com.scen.pojo.Item;
import com.scen.pojo.ItemDesc;
import com.scen.pojo.ItemParamItem;
import com.scen.portal.service.PortalParamDataService;
import com.scen.vo.ItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品详情页面展示
 *
 * @author Scen
 * @date 2018/4/10 10:59
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemDescService itemDescService;

    @Autowired
    private ItemDescCacheService itemDescCacheService;

    @Autowired
    private ItemParamItemService itemParamItemService;

    @Autowired
    private ItemCacheService itemCacheService;

    @Autowired
    private ItemParamItemCacheService itemParamItemCacheService;

    @Autowired
    private PortalParamDataService portalParamDataService;


    /**
     * 根据id查询商品基础信息到详情页
     *
     * @param itemId
     * @param model
     * @return
     */
    @RequestMapping("/item/{itemId}")
    public String showItem(@PathVariable Long itemId, Model model) {
        ItemInfo item = null;
        item = (ItemInfo) itemCacheService.getItemBaseInfo(itemId);
        if (item == null) {
            Item poItem = itemService.getItemById(itemId);
            item = (ItemInfo) poItem;
            itemCacheService.setItemBaseInfo(poItem);
        }
        model.addAttribute("item", item);
        return "item";
    }

    /**
     * 根据id查询商品描述
     *
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/item/desc/{itemId}", produces = MediaType.TEXT_HTML_VALUE + ";charset=UTF-8")
    @ResponseBody
    public String getItemDescById(@PathVariable Long itemId) {
        ItemDesc itemDesc = null;
        itemDesc = itemDescCacheService.getItemDesc(itemId);
        if (itemDesc == null) {
            itemDesc = itemDescService.getItemDesc(itemId);
            itemDescCacheService.setItemDesc(itemDesc);
        }
        return itemDesc.getItemDesc();
    }

    /**
     * 根据id响应商品规格参数
     *
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/item/param/{itemId}", produces = MediaType.TEXT_HTML_VALUE + ";charset=UTF-8")
    @ResponseBody
    public String getItemParamItem(@PathVariable Long itemId) {
        ItemParamItem itemParamItem = null;
        itemParamItem = itemParamItemCacheService.getItemParamItem(itemId);
        if (itemParamItem == null) {
            itemParamItem = itemParamItemService.getItemParamByItemId(itemId);
            itemParamItemCacheService.setItemParamItem(itemParamItem);
        }
        return portalParamDataService.getPortalParamData(itemParamItem.getParamData());
    }
}
