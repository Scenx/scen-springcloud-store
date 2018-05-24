package com.scen.dao;

import com.scen.basedao.BaseDao;
import com.scen.pojo.ItemParam;
import com.scen.vo.ItemParamBean;

import java.util.List;
import java.util.Map;

/**
 * 商品规格参数规则持久层
 *
 * @author Scen
 * @date 2018/5/11 19:48
 */
public interface ItemParamDao extends BaseDao<ItemParam> {

    /**
     * 多条件查询所有规格模版
     *
     * @param map
     * @return
     */
    List<ItemParamBean> getItemParamList(Map<String, Object> map);
}
