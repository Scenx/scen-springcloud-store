package com.scen.item.service.impl;

import com.scen.dao.ItemCatDao;
import com.scen.item.service.ItemCatService;
import com.scen.pojo.ItemCat;
import com.scen.vo.CatNode;
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

    @Override
    public List<?> getPortalCatList(Long parentId) {
        //创建查询条件
        Example example = new Example(ItemCat.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", parentId);
//        执行查询
        List<ItemCat> list = itemCatDao.selectByExample(example);
//        返回值list
        List resultList = new ArrayList<>();
//        向list中添加节点
        for (ItemCat ItemCat : list) {
//            判断是否为父节点
            if (ItemCat.getIsParent()) {
                CatNode catNode = new CatNode();
                if (parentId == 0) {
                    catNode.setName("<a href='/products/" + ItemCat.getId() + ".html'>" + ItemCat.getName() + "</a>");
                } else {
                    catNode.setName(ItemCat.getName());
                }
                catNode.setUrl("/products/" + ItemCat.getId() + ".html");
                catNode.setItem(getPortalCatList(ItemCat.getId()));
                resultList.add(catNode);
            } else {
//                如果是叶子节点
                resultList.add("/products/" + ItemCat.getId() + ".html|" + ItemCat.getName());
            }

        }
        return resultList;
    }
}
