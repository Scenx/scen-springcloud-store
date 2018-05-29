package com.scen.portal;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 门户微服务提供者
 *
 * @author Scen
 * @date 2018/4/28 10:41
 */
@SpringBootApplication
@EnableApolloConfig
public class ScenPortalServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScenPortalServiceApplication.class, args);
    }
}
