package com.scen.portal.controller;

import com.scen.item.service.ItemCatService;
import com.scen.pojo.ItemCat;
import com.scen.search.service.SearchItemService;
import com.scen.vo.SearchResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 搜索商品Controller
 *
 * @author Scen
 * @date 2018/4/8 17:18
 */
@Controller
public class SearchController {

    @Autowired
    private SearchItemService searchItemService;

    @Autowired
    private ItemCatService itemCatService;


    /**
     * 搜索商品
     *
     * @param queryString
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("/search")
    public String search(@RequestParam("q") String queryString, @RequestParam(defaultValue = "1") Integer page, Model model) throws Exception {
        //        查询条件不能为空
        if (StringUtils.isBlank(queryString)) {
            return "search";
        }
        SearchResult searchResult = searchItemService.getPortalItems(queryString, page, null);
//        回显数据
        model.addAttribute("query", queryString);
        model.addAttribute("totalPages", searchResult.getPageCount());
        model.addAttribute("itemList", searchResult.getItemList());
        model.addAttribute("page", page);
        return "search";
    }


    @RequestMapping("/products/{itemCid}")
    public String searchByItemCid(@PathVariable Long itemCid, @RequestParam(defaultValue = "1") Integer page, Model model) throws Exception {
        ItemCat itemCat = itemCatService.getItemCatById(itemCid);
        String queryString = itemCat.getName();
        SearchResult searchResult = searchItemService.getPortalItems(queryString, page, null);
//        回显数据
        model.addAttribute("query", queryString);
        model.addAttribute("totalPages", searchResult.getPageCount());
        model.addAttribute("itemList", searchResult.getItemList());
        model.addAttribute("page", page);
        return "search";
    }

}
