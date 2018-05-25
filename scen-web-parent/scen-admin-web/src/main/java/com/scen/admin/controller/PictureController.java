package com.scen.admin.controller;

import com.scen.admin.service.PictureService;
import com.scen.common.utils.JsonUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传controller
 *
 * @author Scen
 * @date 2018/3/10 21:34
 */
@RestController
@RequestMapping("/pic")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    /**
     * 图片上传
     *
     * @param uploadFile
     * @return
     */
    @RequestMapping("/upload")
    public String pictureUpload(MultipartFile uploadFile) {
        return JsonUtils.objectToJson(pictureService.uploadPicture(uploadFile));
    }
}
