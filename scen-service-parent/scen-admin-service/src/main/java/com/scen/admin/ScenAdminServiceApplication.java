package com.scen.admin;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 后台微服务提供者
 *
 * @author Scen
 * @date 2018/4/28 10:41
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.scen.dao")
@EnableApolloConfig
public class ScenAdminServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScenAdminServiceApplication.class, args);
    }
}
