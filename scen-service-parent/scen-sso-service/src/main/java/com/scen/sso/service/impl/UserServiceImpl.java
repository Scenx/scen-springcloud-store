package com.scen.sso.service.impl;

import com.scen.common.utils.JsonUtils;
import com.scen.dao.UserDao;
import com.scen.pojo.User;
import com.scen.sso.service.UserService;
import com.scen.vo.ScenResult;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * 用户操作服务
 *
 * @author Scen
 * @date 2018/5/26 19:22
 */
@RestController
public class UserServiceImpl implements UserService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private UserDao userDao;

    @Value("${spring.rabbitmq.sso.exchange}")
    private String SSO_EXCHANGE;

    @Value("${spring.rabbitmq.addUserSession.routing-key}")
    private String SSO_ADD_USER_SESSION_ROUTING_KEY;

    @Override
    public ScenResult checkData(String content, Integer type) {
        //        创建查询条件
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
//        对数据进行校验 1、2、3分别代表username、phone、email
//        用户名校验
        if (1 == type) {
            criteria.andEqualTo("username", content);
//            电话校验
        } else if (2 == type) {
            criteria.andEqualTo("phone", content);
//            邮箱校验
        } else {
            criteria.andEqualTo("email", content);
        }
//        执行查询
        List<User> list = userDao.selectByExample(example);
        if (list == null || list.size() == 0) {
            return ScenResult.ok(true);
        }
        return ScenResult.ok(false);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ScenResult createUser(@RequestBody User user) throws Exception {
        user.setCreated(new Date());
        user.setUpdated(new Date());
//        md5加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userDao.insert(user);
        return ScenResult.ok();
    }

    @Override
    public ScenResult userLogin(String username, String password) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        List<User> list = userDao.selectByExample(example);
//      如果没有此用户名
        if (null == list || list.size() == 0) {
            return ScenResult.build(400, "用户名或者密码错误");
        }
        User user = list.get(0);
//        比对密码
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            return ScenResult.build(400, "用户名或者密码错误");
        }
//        生成token
        String token = UUID.randomUUID().toString();
//        保存用户到redis之前把用户密码清空
        user.setPassword(null);
//        把信息通知给cache服务写到redis
        Map<String, String> map = new HashMap();
        map.put("token", token);
        map.put("user", JsonUtils.objectToJson(user));
        rabbitTemplate.convertAndSend(SSO_EXCHANGE, SSO_ADD_USER_SESSION_ROUTING_KEY, map);

//        返回token
        return ScenResult.ok(token);
    }
}
