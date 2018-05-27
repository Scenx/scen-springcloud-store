package com.scen.content.service.hystrix;


import com.scen.content.service.ContentCategoryService;
import com.scen.pojo.ContentCategory;
import com.scen.vo.EUTreeNode;
import com.scen.vo.ScenResult;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 内容分类熔断器
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
    public ContentCategory insertContenCategory(Long parentId, String name) throws Exception {
        return null;
    }

    @Override
    public ScenResult deleteContenCategory(Long id) throws Exception {
        return ScenResult.build(233, "服务不可用");
    }

    @Override
    public void updateContentCategory(ContentCategory contentCategory) throws Exception {

    }
}
