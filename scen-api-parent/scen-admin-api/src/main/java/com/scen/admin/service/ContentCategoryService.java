package com.scen.admin.service;

import com.scen.admin.service.hystrix.ContentCategoryServiceHystrix;
import com.scen.pojo.ContentCategory;
import com.scen.vo.EUTreeNode;
import com.scen.vo.ScenResult;
import org.springframework.cloud.openfeign.FeignClient;

import javax.servlet.http.HttpServletResponse;
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
    List<EUTreeNode> getCategoryList(Long parentId);

    /**
     * 添加分类节点
     *
     * @param parentId
     * @param name
     * @return
     */
    ScenResult insertContenCategory(Long parentId, String name);

    /**
     * 删除分类节点
     *
     * @param id
     * @return
     */
    ScenResult deleteContenCategory(Long id);


    /**
     * 更新分类节点
     *
     * @param contentCategory
     */
    void updateContentCategory(HttpServletResponse response, ContentCategory contentCategory) throws Exception;
}
