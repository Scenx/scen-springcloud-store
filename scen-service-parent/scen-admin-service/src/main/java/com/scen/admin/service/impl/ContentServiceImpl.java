package com.scen.admin.service.impl;

import com.scen.admin.service.ContentService;
import com.scen.pojo.Content;
import com.scen.vo.EUDdataGridResult;
import com.scen.vo.ScenResult;

/**
 * 内容管理服务提供者
 *
 * @author Scen
 * @date 2018/5/12 8:59
 */
public class ContentServiceImpl implements ContentService {
    @Override
    public EUDdataGridResult getContentList(Integer page, Integer rows, Long categoryId) {
        return null;
    }

    @Override
    public ScenResult saveContent(Content content) {
        return ScenResult.build(233, "服务不可用");
    }

    @Override
    public ScenResult deleteContent(Long[] ids) {
        return ScenResult.build(233, "服务不可用");
    }

    @Override
    public ScenResult editContent(Content content) {
        return ScenResult.build(233, "服务不可用");
    }
}
