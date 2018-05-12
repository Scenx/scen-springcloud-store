package com.scen.item.service.hystrix;

import com.scen.item.service.ItemParamItemService;
import com.scen.vo.ScenResult;

/**
 * 商品具体规格参数熔断器
 *
 * @author Scen
 * @date 2018/5/12 9:29
 */
public class ItemParamItemServiceHystrix implements ItemParamItemService {
    @Override
    public ScenResult getItemParamByItemId(Long itemId) {
        return ScenResult.build(233, "服务不可用");
    }
}
