package com.scen.cache.service;

import com.scen.cache.service.hystrix.ContentCacheServiceHystrix;
import com.scen.pojo.Content;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 内容缓存接口
 *
 * @author Scen
 * @date 2018/5/27 18:10
 */
@FeignClient(value = "scen-cache-service", fallback = ContentCacheServiceHystrix.class)
public interface ContentCacheService {

    /**
     * 取出首页打广告内容缓存
     *
     * @param contentCid
     * @return
     */
    @RequestMapping("contentCacheService/getContentList")
    List<Content> getContentList(
            @RequestParam("contentCid") Long contentCid
    );

    /**
     * 把首页打广告位存入缓存
     *
     * @param contentCid
     * @param list
     */
    @RequestMapping("contentCacheService/setContentList")
    void setContentList(
            @RequestParam("contentCid") Long contentCid,
            List<Content> list
    );
}

