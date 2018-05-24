package com.scen.content.service.hystrix;


import com.scen.content.service.ContentService;
import com.scen.pojo.Content;
import com.scen.vo.EUDdataGridResult;
import com.scen.vo.ScenResult;
import org.springframework.stereotype.Component;

/**
 * 内容熔断器
 *
 * @author Scen
 * @date 2018/5/12 8:59
 */
@Component
public class ContentServiceHystrix implements ContentService {
    @Override
    public EUDdataGridResult getContentList(Integer page, Integer rows, Long categoryId) {
        return null;
    }

    @Override
    public ScenResult saveContent(Content content) throws Exception {
        return ScenResult.build(233, "服务不可用");
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
