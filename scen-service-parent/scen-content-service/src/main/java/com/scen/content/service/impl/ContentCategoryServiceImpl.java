package com.scen.content.service.impl;

import com.scen.content.service.ContentCategoryService;
import com.scen.dao.ContentCategoryDao;
import com.scen.pojo.ContentCategory;
import com.scen.vo.EUTreeNode;
import com.scen.vo.ScenResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 内容分类服务提供者
 *
 * @author Scen
 * @date 2018/5/12 8:51
 */
@RestController
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private ContentCategoryDao contentCategoryDao;

    @Override
    public List<EUTreeNode> getCategoryList(Long parentId) {
        //        根据parentId查询节点列表
        Example example = new Example(ContentCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", parentId);
//        执行查询
        List<ContentCategory> list = contentCategoryDao.selectByExample(example);
        List<EUTreeNode> resultList = new ArrayList<>();
        for (ContentCategory ContentCategory : list) {
//            创建一个节点
            EUTreeNode node = new EUTreeNode();
            node.setId(ContentCategory.getId());
            node.setText(ContentCategory.getName());
            node.setState(ContentCategory.getIsParent() ? "closed" : "open");
            resultList.add(node);

        }
        return resultList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ContentCategory insertContenCategory(Long parentId, String name) throws Exception {
        //        创建一个pojo
        ContentCategory contentCategory = new ContentCategory();
        contentCategory.setName(name);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        contentCategory.setIsParent(false);
        contentCategory.setStatus(1);
        contentCategory.setParentId(parentId);
        contentCategory.setSortOrder(1);
//        添加记录
        contentCategoryDao.insert(contentCategory);
//        查看父节点的isParent列是否为true,如果不是true改为true
        ContentCategory parentCat = contentCategoryDao.selectByPrimaryKey(parentId);
        if (!parentCat.getIsParent()) {
            parentCat.setIsParent(true);
            contentCategoryDao.updateByPrimaryKey(parentCat);
        }
        return contentCategory;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ScenResult deleteContenCategory(Long id) {
        ContentCategory contentCategory = contentCategoryDao.selectByPrimaryKey(id);
        if (contentCategory.getIsParent()) {
            Example example = new Example(ContentCategory.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("parentId", id);
            contentCategoryDao.deleteByExample(example);
        }
        contentCategoryDao.deleteByPrimaryKey(id);
        return ScenResult.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateContentCategory(@RequestBody ContentCategory contentCategory) throws Exception {
        contentCategory.setUpdated(new Date());
        contentCategoryDao.updateByPrimaryKeySelective(contentCategory);
    }
}
