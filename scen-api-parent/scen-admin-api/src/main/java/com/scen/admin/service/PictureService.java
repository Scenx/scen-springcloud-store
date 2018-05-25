package com.scen.admin.service;

import com.scen.admin.service.hystrix.PictureServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 图片上传接口
 *
 * @author Scen
 * @date 2018/3/10 20:48
 */
@FeignClient(value = "scen-admin-service", fallback = PictureServiceHystrix.class)
public interface PictureService {
    /**
     * 图片上传
     *
     * @param uploadFile
     * @return
     */
    @RequestMapping("pictureService/uploadPicture")
    Map uploadPicture(MultipartFile uploadFile);
}
