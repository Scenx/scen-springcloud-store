package com.scen.item.service;


import com.scen.item.service.hystrix.ItemParamServiceHystrix;
import com.scen.pojo.ItemParam;
import com.scen.vo.EUDdataGridResult;
import com.scen.vo.ScenResult;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 商品规格参数规则接口
 *
 * @author Scen
 * @date 2018/3/22 10:42
 */
@FeignClient(value = "scen-item-service", fallback = ItemParamServiceHystrix.class)
public interface ItemParamService {
    /**
     * 根据查询对应商品规格
     *
     * @param cid
     * @return
     */
    ScenResult getItemParamByCid(Long cid);

    /**
     * 添加商品规格参数
     *
     * @param itemParam
     * @return
     */
    ScenResult insertItemParam(ItemParam itemParam);

    /**
     * 查询所有规格
     *
     * @param page
     * @param rows
     * @param catName
     * @return
     */
    EUDdataGridResult getItemParamList(Integer page, Integer rows, String catName);

    /**
     * 修改指定类目的规格
     *
     * @param itemParam
     * @return
     */
    ScenResult updateItemParam(ItemParam itemParam);

    /**
     * 根据id批量删除商品类目规格
     *
     * @param ids
     * @return
     */
    ScenResult deleteItemParam(Long[] ids);
}
