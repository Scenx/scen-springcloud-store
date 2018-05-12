package com.scen.admin.service.hystrix;

import com.scen.admin.service.ContentCategoryService;
import com.scen.pojo.ContentCategory;
import com.scen.vo.EUTreeNode;
import com.scen.vo.ScenResult;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 内容分类管理熔断器
 *
 * @author Scen
 * @date 2018/5/12 8:51
 */
@Component
public class ContentCategoryServiceHystrix implements ContentCategoryService {
    @Override
    public List<EUTreeNode> getCategoryList(Long parentId) {
        return null;
    }

    @Override
    public ScenResult insertContenCategory(Long parentId, String name) {
        return ScenResult.build(233, "服务不可用");
    }

    @Override
    public ScenResult deleteContenCategory(Long id) {
        return ScenResult.build(233, "服务不可用");
    }

    @Override
    public void updateContentCategory(HttpServletResponse response, ContentCategory contentCategory) throws Exception {
        response.getWriter().print("服务不可用");
    }
}
