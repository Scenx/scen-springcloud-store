package com.scen.admin.controller;

import com.scen.content.service.ContentService;
import com.scen.pojo.Content;
import com.scen.vo.EUDdataGridResult;
import com.scen.vo.ScenResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 内容管理表现层
 *
 * @author Scen
 * @date 2018/4/3 9:04
 */
@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;


    /**
     * 查询指定分类的所有内容
     *
     * @param page
     * @param rows
     * @param categoryId
     * @return
     */
    @RequestMapping("/query/list")
    public EUDdataGridResult getContentList(Integer page, Integer rows, Long categoryId) {
        return contentService.getContentList(page, rows, categoryId);
    }

    /**
     * 保存内容
     *
     * @param content
     * @return
     */
    @RequestMapping("/save")
    public ScenResult saveContent(Content content) {
        try {
            return contentService.saveContent(content);
        } catch (Exception e) {
            e.printStackTrace();
            return ScenResult.build(500, "保存失败");
        }
    }


    /**
     * 根据id批量删除内容
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public ScenResult deleteContent(Long[] ids) {
        try {
            return contentService.deleteContent(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return ScenResult.build(500, "删除失败");
        }
    }

    /**
     * 更新内容
     *
     * @param content
     * @return
     */
    @RequestMapping("/edit")
    public ScenResult editContent(Content content) {
        try {
            return contentService.editContent(content);
        } catch (Exception e) {
            e.printStackTrace();
            return ScenResult.build(500, "编辑失败");
        }
    }
}
