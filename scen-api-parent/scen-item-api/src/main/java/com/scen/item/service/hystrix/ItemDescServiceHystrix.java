package com.scen.item.service.hystrix;

import com.scen.item.service.ItemDescService;
import com.scen.pojo.ItemDesc;
import org.springframework.stereotype.Component;

/**
 * 商品描述/详情熔断器
 *
 * @author Scen
 * @date 2018/5/12 9:17
 */
@Component
public class ItemDescServiceHystrix implements ItemDescService {
    @Override
    public ItemDesc getItemDesc(Long itemId) {
        return null;
    }
}
