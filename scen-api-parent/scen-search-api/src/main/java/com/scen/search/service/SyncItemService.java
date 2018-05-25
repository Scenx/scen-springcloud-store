package com.scen.search.service;

import com.scen.search.service.hystrix.SyncItemServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品同步接口
 *
 * @author Scen
 * @date 2018/5/18 14:27
 */
@FeignClient(value = "scen-search-service", fallback = SyncItemServiceHystrix.class)
public interface SyncItemService {
    /**
     * 添加同步商品
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/syncItemService/syncAddItem")
    void syncAddItem(
            @RequestParam("itemId") Long itemId
    );


    /**
     * 删除同步商品
     *
     * @param itemId
     */
    @RequestMapping("/syncItemService/syncDelItem")
    void syncDelItem(
            @RequestParam("itemId") Long itemId
    );
}
