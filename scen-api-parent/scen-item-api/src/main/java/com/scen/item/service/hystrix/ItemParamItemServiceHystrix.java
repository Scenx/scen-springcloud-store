package com.scen.item.service.hystrix;

import com.scen.item.service.ItemParamItemService;
import com.scen.pojo.ItemParamItem;
import org.springframework.stereotype.Component;

/**
 * 商品具体规格参数熔断器
 *
 * @author Scen
 * @date 2018/5/12 9:29
 */
@Component
public class ItemParamItemServiceHystrix implements ItemParamItemService {
    @Override
    public ItemParamItem getItemParamByItemId(Long itemId) {
        return null;
    }
}
