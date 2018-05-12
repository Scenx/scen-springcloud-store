package com.scen.admin.service;

import com.scen.admin.service.hystrix.ContentServiceHystrix;
import com.scen.pojo.Content;
import com.scen.vo.EUDdataGridResult;
import com.scen.vo.ScenResult;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 内容管理接口
 *
 * @author Scen
 * @date 2018/4/3 8:57
 */
@FeignClient(value = "scen-admin-service", fallback = ContentServiceHystrix.class)
public interface ContentService {

    /**
     * 查询指定分类的所有内容
     *
     * @param page
     * @param rows
     * @param categoryId
     * @return
     */
    EUDdataGridResult getContentList(Integer page, Integer rows, Long categoryId);

    /**
     * 保存内容
     *
     * @param content
     * @return
     */
    ScenResult saveContent(Content content);


    /**
     * 根据id批量删除内容
     *
     * @param ids
     * @return
     */
    ScenResult deleteContent(Long[] ids);

    /**
     * 更新内容
     *
     * @param content
     * @return
     */
    ScenResult editContent(Content content);
}
