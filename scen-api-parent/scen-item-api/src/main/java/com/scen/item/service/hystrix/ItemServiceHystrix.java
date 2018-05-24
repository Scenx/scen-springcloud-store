package com.scen.item.service.hystrix;

import com.scen.item.service.ItemService;
import com.scen.pojo.Item;
import com.scen.pojo.ItemDesc;
import com.scen.pojo.ItemParamItem;
import com.scen.vo.EUDdataGridResult;
import com.scen.vo.ScenResult;
import com.scen.vo.SolrIf;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 商品熔断器
 *
 * @author Scen
 * @date 2018/5/11 21:50
 */
@Component
public class ItemServiceHystrix implements ItemService {

    @Override
    public Item getItemById(Long itemId) {
        return null;
    }

    @Override
    public EUDdataGridResult getItemList(Integer page, Integer rows, Long id, String title, String catName, Long startPrice, Long endPrice) {
        return null;
    }

    @Override
    public ScenResult createItem(Item item, ItemDesc itemDesc, ItemParamItem itemParamItem) throws Exception {
        return ScenResult.build(233, "服务不可用");
    }

    @Override
    public ScenResult updateItem(Item item, ItemDesc itemDesc, Long itemParamId, String itemParams) throws Exception {
        return ScenResult.build(233, "服务不可用");
    }

    @Override
    public ScenResult deleteItem(List<SolrIf> list) throws Exception {
        return ScenResult.build(233, "服务不可用");
    }

    @Override
    public ScenResult instockItem(List<SolrIf> list) throws Exception {
        return ScenResult.build(233, "服务不可用");
    }

    @Override
    public ScenResult reshelfItem(Long[] ids) throws Exception {
        return ScenResult.build(233, "服务不可用");
    }
}
