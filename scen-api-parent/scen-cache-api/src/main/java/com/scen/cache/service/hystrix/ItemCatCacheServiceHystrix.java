package com.scen.cache.service.hystrix;

import com.scen.cache.service.ItemCatCacheService;
import com.scen.vo.CatResult;
import org.springframework.stereotype.Component;

/**
 * 类目缓存熔断器
 *
 * @author Scen
 * @date 2018/5/29 22:34
 */
@Component
public class ItemCatCacheServiceHystrix implements ItemCatCacheService {

    @Override
    public CatResult getItemCatList() {
        return null;
    }

    @Override
    public void setItemCatList(String cacheString) {

    }
}
