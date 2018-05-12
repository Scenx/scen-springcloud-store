package com.scen.item.service.hystrix;

import com.scen.item.service.ItemCatService;
import com.scen.vo.EUTreeNode;

import java.util.List;

/**
 * 商品分类熔断器
 *
 * @author Scen
 * @date 2018/5/12 9:15
 */
public class ItemCatServiceHystrix implements ItemCatService {
    @Override
    public List<EUTreeNode> getCatList(Long parentId) {
        return null;
    }
}
