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
     * @param parentId
     * @return
     */
    @RequestMapping("/ContentCategoryService/getCategoryList")
    List<EUTreeNode> getCategoryList(
            @RequestParam("parentId") Long parentId
    );

    /**
     * 添加分类节点
     *
     * @param parentId
     * @param name
     * @return
     * @throws Exception
     */
    @RequestMapping("/ContentCategoryService/insertContenCategory")
    ScenResult insertContenCategory(
            @RequestParam("parentId") Long parentId,
            @RequestParam("name") String name
    ) throws Exception;

    /**
     * 删除分类节点
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/ContentCategoryService/deleteContenCategory")
    ScenResult deleteContenCategory(
            @RequestParam("id") Long id
    ) throws Exception;


    /**
     * 更新分类节点
     *
     * @param contentCategory
     * @throws Exception
     */
    @RequestMapping("/ContentCategoryService/updateContentCategory")
    void updateContentCategory(
            ContentCategory contentCategory
    ) throws Exception;
}
