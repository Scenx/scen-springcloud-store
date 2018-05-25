package com.scen.dao;

import com.scen.basedao.BaseDao;
import com.scen.pojo.Item;
import com.scen.vo.ItemBean;
import com.scen.vo.SearchItem;

import java.util.List;
import java.util.Map;

/**
 * 商品持久层
 *
 * @author Scen
 * @date 2018/5/11 19:46
 */
public interface ItemDao extends BaseDao<Item> {

    /**
     * 多条件查询所有商品
     *
     * @param map
     * @return
     */
    List<ItemBean> getSearchItemList(Map<String, Object> map);

    /**
     * 根据id取商品索引库所需字段
     *
     * @param itemId
     * @return
     */
    SearchItem getItem(Long itemId);

    /**
     * 检索所有solr所需的商品字段
     *
     * @return
     */
    List<SearchItem> getItemList();
}
