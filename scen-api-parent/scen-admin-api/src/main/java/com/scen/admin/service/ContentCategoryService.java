package com.scen.admin.service;

import com.scen.admin.service.hystrix.ContentCategoryServiceHystrix;
import com.scen.pojo.ContentCategory;
import com.scen.vo.EUTreeNode;
import com.scen.vo.ScenResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 内容分类管理接口
 *
 * @author Scen
 * @date 2018/4/2 15:12
 */
@FeignClient(value = "scen-admin-service", fallback = ContentCategoryServiceHystrix.class)
public interface ContentCategoryService {
    /**
     * 获取分类节点
     *
     * @param parentId 父节点id
     * @return 指定父id下的内容分类
     */
    @RequestMapping("/contentCategoryService/getCategoryList")
    List<EUTreeNode> getCategoryList(
            @RequestParam("parentId") Long parentId
    );

    /**
     * 添加分类节点
     *
     * @param parentId 父节点id
     * @param name 节点名
     * @return 自定义响应结构
     * @throws Exception 抛出异常回滚事务
     */
    @RequestMapping("/contentCategoryService/insertContenCategory")
    ScenResult insertContenCategory(
            @RequestParam("parentId") Long parentId,
            @RequestParam("name") String name
    ) throws Exception;

    /**
     * 删除分类节点
     *
     * @param id 节点id
     * @return 自定义响应结构
     * @throws Exception 抛出异常回滚事务
     */
    @RequestMapping("/contentCategoryService/deleteContenCategory")
    ScenResult deleteContenCategory(
            @RequestParam("id") Long id
    ) throws Exception;


    /**
     * 更新分类节点
     *
     * @param contentCategory 内容分类对象
     * @throws Exception 抛出异常回滚事务
     */
    @RequestMapping("/contentCategoryService/updateContentCategory")
    void updateContentCategory(
            ContentCategory contentCategory
    ) throws Exception;
}
