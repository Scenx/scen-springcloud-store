package com.scen.admin.service.hystrix;

import com.scen.admin.service.PictureService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 图片上传熔断器
 *
 * @author Scen
 * @date 2018/5/12 9:45
 */
@Component
public class PictureServiceHystrix implements PictureService {
    @Override
    public Map uploadPicture(MultipartFile uploadFile) {
        Map resultMap = new HashMap();
        resultMap.put("error", 1);
        resultMap.put("message", "文件上传发生异常");
        return resultMap;
    }
}
