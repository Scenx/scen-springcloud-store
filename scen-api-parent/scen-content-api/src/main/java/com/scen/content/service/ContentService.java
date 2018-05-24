package com.scen.content.service;


import com.scen.content.service.hystrix.ContentServiceHystrix;
import com.scen.pojo.Content;
import com.scen.vo.EUDdataGridResult;
import com.scen.vo.ScenResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 内容接口
 *
 * @author Scen
 * @date 2018/4/3 8:57
 */
@FeignClient(value = "scen-content-service", fallback = ContentServiceHystrix.class)
public interface ContentService {

    /**
     * 查询指定分类的所有内容
     *
     * @param page       页数
     * @param rows       行数
     * @param categoryId 分类id
     * @return 内容信息
     */
    @RequestMapping("/contentService/getContentList")
    EUDdataGridResult getContentList(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows,
            @RequestParam("categoryId") Long categoryId
    );

    /**
     * 保存内容
     *
     * @param content 内容对象
     * @return 自定义响应结构
     * @throws Exception 抛出异常回滚事务
     */
    @RequestMapping("/contentService/saveContent")
    ScenResult saveContent(
            Content content
    ) throws Exception;


    /**
     * 根据id批量删除内容
     *
     * @param ids 内容id数组
     * @return 自定义响应结构
     * @throws Exception 抛出异常回滚事务
     */
    @RequestMapping("/contentService/deleteContent")
    ScenResult deleteContent(
            Long[] ids
    ) throws Exception;

    /**
     * 更新内容
     *
     * @param content 内容对象
     * @return 自定义响应结构
     * @throws Exception 抛出异常回滚事务
     */
    @RequestMapping("/contentService/editContent")
    ScenResult editContent(
            Content content
    ) throws Exception;
}
