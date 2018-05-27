package com.scen.cache.service.hystrix;

import com.scen.cache.service.ItemDescCacheService;
import com.scen.pojo.Item;
import com.scen.pojo.ItemDesc;
import org.springframework.stereotype.Component;

/**
 * 商品描述缓存熔断器
 *
 * @author Scen
 * @date 2018/5/27 22:06
 */
@Component
public class ItemDescCacheServiceHystrix implements ItemDescCacheService {

    @Override
    public ItemDesc getItemDesc(Long itemId) {
        return null;
    }

    @Override
    public void setItemDesc(ItemDesc itemDesc) {

    }
}
