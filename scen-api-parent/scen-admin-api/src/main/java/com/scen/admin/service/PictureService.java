package com.scen.admin.service;

import com.scen.admin.service.hystrix.PictureServiceHystrix;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 图片上传接口
 *
 * @author Scen
 * @date 2018/3/10 20:48
 */
@FeignClient(value = "scen-admin-service", fallback = PictureServiceHystrix.class, configuration = PictureService.MultipartSupportConfig.class)
public interface PictureService {
    /**
     * 图片上传
     *
     * @param uploadFile
     * @return
     */
    @RequestMapping(value = "pictureService/uploadPicture", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Map uploadPicture(@RequestPart("uploadFile") MultipartFile uploadFile);

    class MultipartSupportConfig {
        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder();
        }
    }
}
