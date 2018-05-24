package com.scen.item.service;

import com.scen.item.service.hystrix.ItemCatServiceHystrix;
import com.scen.vo.EUTreeNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @RequestMapping("/itemCatService/getCatList")
    List<EUTreeNode> getCatList(
            @RequestParam("parentId") Long parentId
    );
}
