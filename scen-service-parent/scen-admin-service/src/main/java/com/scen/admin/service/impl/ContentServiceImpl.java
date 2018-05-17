package com.scen.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scen.admin.service.ContentService;
import com.scen.dao.ContentDao;
import com.scen.pojo.Content;
import com.scen.vo.EUDdataGridResult;
import com.scen.vo.ScenResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * 内容管理服务提供者
 *
 * @author Scen
 * @date 2018/5/12 8:59
 */
@RestController
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentDao contentDao;

    @Override
    public EUDdataGridResult getContentList(Integer page, Integer rows, Long categoryId) {
        //        查询商品列表
//        分页处理
        PageHelper.startPage(page, rows);
        Example example = new Example(Content.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryId", categoryId);
        List<Content> list = contentDao.selectByExample(example);
//        创建一个返回值对象
        EUDdataGridResult result = new EUDdataGridResult();
        result.setRows(list);
//        取记录总条数
        PageInfo<Content> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ScenResult saveContent(Content content) throws Exception {
        //        补全内容
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentDao.insert(content);
//        添加缓存同步逻辑
//        try {
//            HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return ScenResult.ok();
    }

    @Override
    public ScenResult deleteContent(Long[] ids) throws Exception {
        return ScenResult.build(233, "服务不可用");
    }

    @Override
    public ScenResult editContent(Content content) {
        return ScenResult.build(233, "服务不可用");
    }
}
