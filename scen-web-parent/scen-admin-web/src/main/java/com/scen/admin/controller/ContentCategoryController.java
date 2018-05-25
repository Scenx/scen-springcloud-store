package com.scen.admin.controller;


import com.scen.content.service.ContentCategoryService;
import com.scen.pojo.ContentCategory;
import com.scen.vo.EUTreeNode;
import com.scen.vo.ScenResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 内容分类管理表现层
 *
 * @author Scen
 * @date 2018/4/2 15:29
 */
@RestController
@RequestMapping("/content/category")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;


    /**
     * 查询分类节点
     *
     * @param parentId
     * @return
     */
    @RequestMapping("/list")
    public List<EUTreeNode> getContentCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        return contentCategoryService.getCategoryList(parentId);
    }

    /**
     * 添加分类节点
     *
     * @param parentId
     * @param name
     * @return
     */
    @RequestMapping("/create")
    public ScenResult createContentCategory(Long parentId, String name) {
        try {
            return contentCategoryService.insertContenCategory(parentId, name);
        } catch (Exception e) {
            e.printStackTrace();
            return ScenResult.build(500, "添加失败");
        }
    }


    /**
     * 删除内容分类节点
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public ScenResult deleteContentCategory(Long id) {
        try {
            return contentCategoryService.deleteContenCategory(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ScenResult.build(500, "删除失败");
        }
    }


    /**
     * 更新分类节点
     *
     * @param contentCategory
     */
    @RequestMapping("/update")
    public void updateContentCategory(ContentCategory contentCategory) {
        try {
            contentCategoryService.updateContentCategory(contentCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
