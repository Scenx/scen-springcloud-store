package com.scen.cache.service.hystrix;

import com.scen.cache.service.SyncCacheService;
import org.springframework.stereotype.Component;

/**
 * 内容同步熔断器
 *
 * @author Scen
 * @date 2018/5/18 14:28
 */
@Component
public class SyncCacheServiceHystrix implements SyncCacheService {
    @Override
    public void syncContent(Long contentCid) {

    }
}
