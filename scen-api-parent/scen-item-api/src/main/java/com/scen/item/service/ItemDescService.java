package com.scen.item.service;

import com.scen.item.service.hystrix.ItemDescServiceHystrix;
import com.scen.vo.ScenResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品描述/详情接口
 *
 * @author Scen
 * @date 2018/3/23 15:25
 */
@FeignClient(value = "scen-item-service", fallback = ItemDescServiceHystrix.class)
public interface ItemDescService {

    /**
     * 根据商品id查询商品详情
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/itemDescService/getItemDesc")
    ScenResult getItemDesc(
            @RequestParam("itemId") Long itemId
    );
}
