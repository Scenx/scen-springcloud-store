package com.scen.item.service.impl;

import com.scen.dao.ItemCatDao;
import com.scen.item.service.ItemCatService;
import com.scen.pojo.ItemCat;
import com.scen.vo.EUTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类服务
 *
 * @author Scen
 * @date 2018/5/24 21:03
 */
@RestController
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private ItemCatDao itemCatDao;

    @Override
    public List<EUTreeNode> getCatList(Long parentId) {
//        创建查询条件
        Example example = new Example(ItemCat.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", parentId);
//        根据条件查询
        List<ItemCat> list = itemCatDao.selectByExample(example);
        List<EUTreeNode> resultList = new ArrayList<>();
//        把列表转换成treeNodelist
        for (ItemCat itemCat : list) {
            EUTreeNode node = new EUTreeNode();
            node.setId(itemCat.getId());
            node.setText(itemCat.getName());
            node.setState(itemCat.getIsParent() ? "closed" : "open");
            resultList.add(node);
        }
        return resultList;
    }

    @Override
    public ItemCat getItemCatById(Long itemCid) {
        return itemCatDao.selectByPrimaryKey(itemCid);
    }
}
