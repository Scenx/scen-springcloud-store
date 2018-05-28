package com.scen.order;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 商品微服务提供者
 *
 * @author Scen
 * @date 2018/4/28 10:41
 */
@SpringBootApplication
@MapperScan(basePackages = "com.scen.dao")
@EnableApolloConfig
public class ScenOrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScenOrderServiceApplication.class, args);
    }
}
