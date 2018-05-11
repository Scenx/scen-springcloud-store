package com.scen.item.service;

import com.scen.item.service.hystrix.ItemServiceHystrix;
import com.scen.pojo.Item;
import com.scen.pojo.ItemDesc;
import com.scen.pojo.ItemParamItem;
import com.scen.vo.EUDdataGridResult;
import com.scen.vo.ScenResult;
import com.scen.vo.SolrIf;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

/**
 * 商品接口
 *
 * @author Scen
 * @date 2018/5/11 21:41
 */
@FeignClient(value = "scen-item-service", fallback = ItemServiceHystrix.class)
public interface ItemService {
    /**
     * 根据id查询商品信息
     *
     * @param itemId
     * @return
     */
    Item getItemById(Long itemId);


    /**
     * 查询所有商品并分页
     *
     * @param page
     * @param rows
     * @param id
     * @param title
     * @param catName
     * @param startPrice
     * @param endPrice
     * @return
     */
    EUDdataGridResult getItemList(Integer page, Integer rows, Long id, String title, String catName, Long startPrice, Long endPrice);


    /**
     * 保存商品
     *
     * @param item
     * @param itemDesc
     * @param itemParamItem
     * @return
     * @throws Exception
     */
    ScenResult createItem(Item item, ItemDesc itemDesc, ItemParamItem itemParamItem);


    /**
     * 更新商品
     *
     * @param item
     * @param itemDesc
     * @param itemParamId
     * @param itemParams
     * @return
     * @throws Exception
     */
    ScenResult updateItem(Item item, ItemDesc itemDesc, Long itemParamId, String itemParams);

    /**
     * 根据id批量删除商品
     *
     * @param list
     * @return
     */
    ScenResult deleteItem(List<SolrIf> list);


    /**
     * 根据id批量下架商品
     *
     * @param list
     * @return
     */
    ScenResult instockItem(List<SolrIf> list);

    /**
     * 根据id批量上架商品
     *
     * @param ids
     * @return
     */
    ScenResult reshelfItem(Long[] ids);
}
