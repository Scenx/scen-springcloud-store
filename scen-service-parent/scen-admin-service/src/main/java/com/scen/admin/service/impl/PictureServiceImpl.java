package com.scen.admin.service.impl;

import com.scen.admin.service.PictureService;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 图片上传服务提供者
 *
 * @author Scen
 * @date 2018/5/12 9:45
 */
public class PictureServiceImpl implements PictureService {
    @Override
    public Map uploadPicture(MultipartFile uploadFile) {
        Map resultMap = new HashMap();
        resultMap.put("error", 1);
        resultMap.put("message", "文件上传发生异常");
        return resultMap;
    }
}
