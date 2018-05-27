package com.scen.item.service;


import com.scen.item.service.hystrix.ItemParamServiceHystrix;
import com.scen.pojo.ItemParam;
import com.scen.vo.EUDdataGridResult;
import com.scen.vo.ScenResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品规格参数规则接口
 *
 * @author Scen
 * @date 2018/3/22 10:42
 */
@FeignClient(value = "scen-item-service", fallback = ItemParamServiceHystrix.class)
public interface ItemParamService {
    /**
     * 根据cid查询对应商品规格
     *
     * @param cid
     * @return
     */
    @RequestMapping("/itemParamService/getItemParamByCid")
    ItemParam getItemParamByCid(
            @RequestParam("cid") Long cid
    );

    /**
     * 添加商品规格参数
     *
     * @param itemParam
     * @return
     * @throws Exception
     */
    @RequestMapping("/itemParamService/insertItemParam")
    ScenResult insertItemParam(ItemParam itemParam) throws Exception;

    /**
     * 查询所有规格
     *
     * @param page
     * @param rows
     * @param catName
     * @return
     */
    @RequestMapping("/itemParamService/getItemParamList")
    EUDdataGridResult getItemParamList(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows,
            @RequestParam("catName") String catName
    );

    /**
     * 修改指定类目的规格
     *
     * @param itemParam
     * @return
     * @throws Exception
     */
    @RequestMapping("/itemParamService/updateItemParam")
    ScenResult updateItemParam(ItemParam itemParam) throws Exception;

    /**
     * 根据id批量删除商品类目规格
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping("/itemParamService/deleteItemParam")
    ScenResult deleteItemParam(Long[] ids) throws Exception;
}
