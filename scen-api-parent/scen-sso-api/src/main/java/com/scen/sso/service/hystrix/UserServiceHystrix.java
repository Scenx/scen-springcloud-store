package com.scen.sso.service.hystrix;

import com.scen.pojo.User;
import com.scen.sso.service.UserService;
import com.scen.vo.ScenResult;
import org.springframework.stereotype.Component;

/**
 * 用户操作熔断器
 *
 * @author Scen
 * @date 2018/5/26 19:01
 */
@Component
public class UserServiceHystrix implements UserService {
    @Override
    public ScenResult checkData(String content, Integer type) {
        return ScenResult.build(233, "服务不可用");
    }

    @Override
    public ScenResult createUser(User user) throws Exception {
        return ScenResult.build(233, "服务不可用");
    }

    @Override
    public ScenResult userLogin(String username, String password) {
        return ScenResult.build(233, "服务不可用");
    }


    @Override
    public ScenResult logoutUserByToken(String token) {
        return ScenResult.build(233, "服务不可用");
    }
}
