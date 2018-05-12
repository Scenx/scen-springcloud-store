package com.scen.item.service.hystrix;

import com.scen.item.service.ItemParamService;
import com.scen.pojo.ItemParam;
import com.scen.vo.EUDdataGridResult;
import com.scen.vo.ScenResult;

/**
 * 商品规格参数规则熔断器
 *
 * @author Scen
 * @date 2018/5/12 9:39
 */
public class ItemParamServiceHystrix implements ItemParamService {
    @Override
    public ScenResult getItemParamByCid(Long cid) {
        return ScenResult.build(233, "服务不可用");
    }

    @Override
    public ScenResult insertItemParam(ItemParam itemParam) {
        return ScenResult.build(233, "服务不可用");
    }

    @Override
    public EUDdataGridResult getItemParamList(Integer page, Integer rows, String catName) {
        return null;
    }

    @Override
    public ScenResult updateItemParam(ItemParam itemParam) {
        return ScenResult.build(233, "服务不可用");
    }

    @Override
    public ScenResult deleteItemParam(Long[] ids) {
        return ScenResult.build(233, "服务不可用");
    }
}
