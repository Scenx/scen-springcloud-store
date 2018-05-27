package com.scen.cache.service.hystrix;

import com.scen.cache.service.ItemParamItemCacheService;
import com.scen.pojo.ItemParamItem;
import org.springframework.stereotype.Component;

/**
 * 商品具体参数缓存熔断器
 *
 * @author Scen
 * @date 2018/5/27 22:42
 */
@Component
public class ItemParamItemCacheServiceHystrix implements ItemParamItemCacheService {

    @Override
    public ItemParamItem getItemParamItem(Long itemId) {
        return null;
    }

    @Override
    public void setItemParamItem(ItemParamItem itemParamItem) {

    }
}
