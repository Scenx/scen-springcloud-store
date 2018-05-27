package com.scen.portal.controller;

import com.scen.pojo.ItemInfo;
import com.scen.portal.service.ItemService;
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

    /**
     * 根据id查询商品基础信息到详情页
     *
     * @param itemId
     * @param model
     * @return
     */
    @RequestMapping("/item/{itemId}")
    public String showItem(@PathVariable Long itemId, Model model) {
        ItemInfo item = itemService.getItemById(itemId);
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
        return itemService.getItemDescById(itemId);
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
        return itemService.getItemParamItem(itemId);
    }
}
