package com.scen.cache.service;

import com.scen.cache.service.hystrix.SyncCacheServiceHystrix;
import com.scen.vo.ScenResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 缓存同步接口
 *
 * @author Scen
 * @date 2018/5/18 14:27
 */
@FeignClient(value = "scen-cache-service", fallback = SyncCacheServiceHystrix.class)
public interface SyncCacheService {
    /**
     * 同步内容
     *
     * @param contentCid
     * @return
     */
    @RequestMapping("/syncCacheService/syncContent")
    ScenResult syncContent(
            @RequestParam("contentCid") Long contentCid
    );
}
