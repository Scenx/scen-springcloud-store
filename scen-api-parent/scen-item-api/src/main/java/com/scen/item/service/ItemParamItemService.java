package com.scen.item.service;


import com.scen.item.service.hystrix.ItemParamItemServiceHystrix;
import com.scen.vo.ScenResult;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 商品具体规格参数业务层接口
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
    ScenResult getItemParamByItemId(Long itemId);
}
