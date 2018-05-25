package com.scen.item.service;

import com.scen.item.service.hystrix.ItemServiceHystrix;
import com.scen.pojo.Item;
import com.scen.vo.EUDdataGridResult;
import com.scen.vo.ScenResult;
import com.scen.vo.SolrIf;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

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
    @RequestMapping("/itemService/getItemById")
    Item getItemById(
            @RequestParam("itemId") Long itemId
    );


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
    @RequestMapping("/itemService/getItemList")
    EUDdataGridResult getItemList(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows,
            @RequestParam("id") Long id,
            @RequestParam("title") String title,
            @RequestParam("catName") String catName,
            @RequestParam("startPrice") Long startPrice,
            @RequestParam("endPrice") Long endPrice
    );


    /**
     * 保存商品
     * @param itemMap
     * @return
     * @throws Exception
     */
    @RequestMapping("/itemService/createItem")
    ScenResult createItem(Map<String, Object> itemMap) throws Exception;


    /**
     * 更新商品
     * @param itemMap
     * @param itemParamId
     * @param itemParams
     * @return
     * @throws Exception
     */
    @RequestMapping("/itemService/updateItem")
    ScenResult updateItem(
            Map<String, Object> itemMap,
            @RequestParam("itemParamId") Long itemParamId,
            @RequestParam("itemParams") String itemParams
    ) throws Exception;

    /**
     * 根据id批量删除商品
     *
     * @param list
     * @return
     * @throws Exception
     */
    @RequestMapping("/itemService/deleteItem")
    ScenResult deleteItem(List<SolrIf> list) throws Exception;


    /**
     * 根据id批量下架商品
     *
     * @param list
     * @return
     * @throws Exception
     */
    @RequestMapping("/itemService/instockItem")
    ScenResult instockItem(List<SolrIf> list) throws Exception;

    /**
     * 根据id批量上架商品
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping("/itemService/reshelfItem")
    ScenResult reshelfItem(Long[] ids) throws Exception;
}
