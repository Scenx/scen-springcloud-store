package com.scen.cache.service;

import com.scen.cache.service.hystrix.ItemParamItemCacheServiceHystrix;
import com.scen.pojo.ItemParamItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品具体参数缓存接口
 *
 * @author Scen
 * @date 2018/5/27 21:38
 */
@FeignClient(value = "scen-cache-service", fallback = ItemParamItemCacheServiceHystrix.class)
public interface ItemParamItemCacheService {

    /**
     * 获取商品具体参数缓存
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/itemParamItemCacheService/getItemParamItem")
    ItemParamItem getItemParamItem(@RequestParam("itemId") Long itemId);


    /**
     * 存入商品具体参数缓存
     *
     * @param itemParamItem
     * @return
     */
    @RequestMapping("/itemParamItemCacheService/setItemParamItem")
    void setItemParamItem(ItemParamItem itemParamItem);
}
