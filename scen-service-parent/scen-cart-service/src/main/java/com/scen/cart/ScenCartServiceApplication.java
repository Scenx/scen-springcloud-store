package com.scen.cart;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 购物车微服务提供者
 *
 * @author Scen
 * @date 2018/5/17 17:16
 */
@SpringBootApplication
@EnableApolloConfig
public class ScenCartServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScenCartServiceApplication.class, args);
    }
}
