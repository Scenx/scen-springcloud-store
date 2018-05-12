package com.scen.item.service.hystrix;

import com.scen.item.service.ItemDescService;
import com.scen.vo.ScenResult;

/**
 * 商品描述/详情熔断器
 *
 * @author Scen
 * @date 2018/5/12 9:17
 */
public class ItemDescServiceHystrix implements ItemDescService {
    @Override
    public ScenResult getItemDesc(Long itemId) {
        return ScenResult.build(233, "服务不可用");
    }
}
