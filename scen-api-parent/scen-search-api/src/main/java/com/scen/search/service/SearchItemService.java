package com.scen.search.service;

import com.scen.search.service.hystrix.SearchItemServiceHystrix;
import com.scen.vo.ScenResult;
import com.scen.vo.SearchResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 商品检索接口
 *
 * @author Scen
 * @date 2018/5/25 15:46
 */
@FeignClient(value = "scen-search-service", fallback = SearchItemServiceHystrix.class)
public interface SearchItemService {

    /**
     * 导入查询出来的商品到solr服务器
     *
     * @return
     */
    @RequestMapping("/searchItemService/importAllItems")
    ScenResult importAllItems();

    /**
     * 检索门户向用户展示的商品
     * @param queryString
     * @param page
     * @param rows
     * @throws Exception
     * @return
     */
    @RequestMapping("/searchItemService/getPortalItems")
    SearchResult getPortalItems(
            @RequestParam("queryString") String queryString,
            @RequestParam(value = "page",defaultValue ="1") Integer page,
            @RequestParam(value = "rows", defaultValue = "69") Integer rows
    ) throws Exception;
}
