package com.scen.admin.service.impl;

import com.scen.admin.service.AdminUserService;
import com.scen.dao.AdminUserDao;
import com.scen.pojo.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.Set;

/**
 * 后台用户服务提供者
 *
 * @author Scen
 * @date 2018/5/11 22:30
 */
@RestController
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;

    @Override
    public AdminUser getByUserName(String username) {
        Example example = new Example(AdminUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        return adminUserDao.selectOneByExample(example);
    }

    @Override
    public Set<String> getRoles(String username) {
        return adminUserDao.getRoles(username);
    }

    @Override
    public Set<String> getPermissions(String username) {
        return adminUserDao.getPermissions(username);
    }
}
