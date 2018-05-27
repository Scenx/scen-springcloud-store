package com.scen.item.service;


import com.scen.item.service.hystrix.ItemParamItemServiceHystrix;
import com.scen.pojo.ItemParamItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品具体规格参数接口
 *
 * @author Scen
 * @date 2018/3/22 13:40
 */
@FeignClient(value = "scen-item-service", fallback = ItemParamItemServiceHystrix.class)
public interface ItemParamItemService {
    /**
     * 根据id查询商品规格参数
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/itemParamItemService/getItemParamByItemId")
    ItemParamItem getItemParamByItemId(
            @RequestParam("itemId") Long itemId
    );
}
