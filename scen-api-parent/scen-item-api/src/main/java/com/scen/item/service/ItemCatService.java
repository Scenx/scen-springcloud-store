package com.scen.item.service;

import com.scen.item.service.hystrix.ItemCatServiceHystrix;
import com.scen.vo.EUTreeNode;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

/**
 * 商品分类接口
 *
 * @author Scen
 * @date 2018/3/10 12:29
 */
@FeignClient(value = "scen-item-service", fallback = ItemCatServiceHystrix.class)

public interface ItemCatService {
    /**
     * 树形节点列表
     *
     * @param parentId
     * @return
     */
    List<EUTreeNode> getCatList(Long parentId);
}
