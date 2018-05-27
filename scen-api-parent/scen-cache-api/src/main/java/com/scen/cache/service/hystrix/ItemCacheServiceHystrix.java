package com.scen.cache.service.hystrix;

import com.scen.cache.service.ItemCacheService;
import com.scen.pojo.Item;
import org.springframework.stereotype.Component;

/**
 * 商品缓存熔断器
 *
 * @author Scen
 * @date 2018/5/27 21:38
 */
@Component
public class ItemCacheServiceHystrix implements ItemCacheService {

    @Override
    public Item getItemBaseInfo(Long itemId) {
        return null;
    }

    @Override
    public void setItemBaseInfo(Item item) {

    }
}
