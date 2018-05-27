package com.scen.item.service.impl;

import com.scen.dao.ItemParamDao;
import com.scen.dao.ItemParamItemDao;
import com.scen.item.service.ItemParamItemService;
import com.scen.pojo.ItemParamItem;
import com.scen.vo.ScenResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 商品具体规格参数
 *
 * @author Scen
 * @date 2018/5/24 23:24
 */
@RestController
public class ItemParamItemServiceImpl implements ItemParamItemService {

    @Autowired
    private ItemParamItemDao itemParamItemDao;

    @Override
    public ItemParamItem getItemParamByItemId(Long itemId) {
        Example example = new Example(ItemParamItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        List<ItemParamItem> list = itemParamItemDao.selectByExample(example);
        if (list == null || list.size() == 0) {
            return null;
        }
        ItemParamItem itemParamItem = list.get(0);
        return itemParamItem;
    }
}
