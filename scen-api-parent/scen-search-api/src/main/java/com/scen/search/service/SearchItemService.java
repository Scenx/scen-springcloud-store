package com.scen.search.service;

import com.scen.search.service.hystrix.SearchItemServiceHystrix;
import com.scen.vo.ScenResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
