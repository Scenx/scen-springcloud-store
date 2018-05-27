package com.scen.portal.controller;

import com.scen.common.utils.HttpClientUtil;
import com.scen.pojo.ScenResult;
import com.scen.pojo.SearchResult;
import com.scen.pojo.TbItemCat;
import com.scen.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Autowired
    private SearchService searchService;

    /**
     * 搜索商品
     *
     * @param queryString
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("/search")
    public String search(@RequestParam("q") String queryString, @RequestParam(defaultValue = "1") Integer page, Model model) {
        SearchResult searchResult = searchService.search(queryString, page);
//        回显数据
        model.addAttribute("query", queryString);
        model.addAttribute("totalPages", searchResult.getPageCount());
        model.addAttribute("itemList", searchResult.getItemList());
        model.addAttribute("page", page);
        return "search";
    }


    @RequestMapping("/products/{itemCid}")
    public String searchByItemCid(@PathVariable Long itemCid, @RequestParam(defaultValue = "1") Integer page, Model model) {
        String json = HttpClientUtil.doGet(REST_BASE_URL + "/itemCat/" + itemCid);
        ScenResult scenResultItemCat = ScenResult.formatToPojo(json, TbItemCat.class);
        TbItemCat itemCat = (TbItemCat) scenResultItemCat.getData();
        String queryString = itemCat.getName();
        SearchResult searchResult = searchService.search(queryString, page);
//        回显数据
        model.addAttribute("query", queryString);
        model.addAttribute("totalPages", searchResult.getPageCount());
        model.addAttribute("itemList", searchResult.getItemList());
        model.addAttribute("page", page);
        return "search";
    }

}
