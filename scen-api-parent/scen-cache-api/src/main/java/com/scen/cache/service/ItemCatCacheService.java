package com.scen.cache.service;

import com.scen.cache.service.hystrix.ItemCatCacheServiceHystrix;
import com.scen.vo.CatResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品类目缓存接口
 *
 * @author Scen
 * @date 2018/5/27 21:38
 */
@FeignClient(value = "scen-cache-service", fallback = ItemCatCacheServiceHystrix.class)
public interface ItemCatCacheService {

    /**
     * 获取类目基础信息缓存
     *
     * @return
     */
    @RequestMapping("/itemCatCacheService/getItemCatList")
    CatResult getItemCatList();


    /**
     * 存入类目信息缓存
     *
     * @param cacheString
     * @return
     */
    @RequestMapping("/itemCatCacheService/setItemCatList")
    void setItemCatList(@RequestParam("cacheString") String cacheString);
}
