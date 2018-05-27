package com.scen.cache.service;

import com.scen.cache.service.hystrix.ItemDescCacheServiceHystrix;
import com.scen.pojo.ItemDesc;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品描述缓存接口
 *
 * @author Scen
 * @date 2018/5/27 21:38
 */
@FeignClient(value = "scen-cache-service", fallback = ItemDescCacheServiceHystrix.class)
public interface ItemDescCacheService {

    /**
     * 获取商品基础信息缓存
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/itemDescCacheService/getItemDesc")
    ItemDesc getItemDesc(@RequestParam("itemId") Long itemId);


    /**
     * 存入商品基础信息缓存
     *
     * @param itemDesc
     * @return
     */
    @RequestMapping("/itemDescCacheService/setItemDesc")
    void setItemDesc(ItemDesc itemDesc);
}
