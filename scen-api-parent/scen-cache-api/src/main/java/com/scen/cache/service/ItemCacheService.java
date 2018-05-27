package com.scen.cache.service;

import com.scen.cache.service.hystrix.ItemCacheServiceHystrix;
import com.scen.pojo.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品缓存接口
 *
 * @author Scen
 * @date 2018/5/27 21:38
 */
@FeignClient(value = "scen-cache-service", fallback = ItemCacheServiceHystrix.class)
public interface ItemCacheService {

    /**
     * 获取商品基础信息缓存
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/itemCacheService/getItemBaseInfo")
    Item getItemBaseInfo(@RequestParam("itemId") Long itemId);


    /**
     * 存入商品基础信息缓存
     *
     * @param item
     * @return
     */
    @RequestMapping("/itemCacheService/setItemBaseInfo")
    void setItemBaseInfo(Item item);
}
