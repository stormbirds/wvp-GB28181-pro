package com.genersoft.iot.vmp.service.impl;

import com.genersoft.iot.vmp.service.IUserService;
import com.genersoft.iot.vmp.storager.dao.dto.Role;
import com.genersoft.iot.vmp.storager.dao.dto.User;
import com.genersoft.iot.vmp.utils.DateUtil;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;


@SpringBootTest
class UserServiceImplTest {

    @Resource
    private IUserService userService;


    @org.junit.jupiter.api.Test
    void getAllUser() {
        List<User> allUsers = userService.getAllUsers();
        User admin = userService.getUser("admin", "21232f297a57a5a743894a0e4a801fc3");
        User admin1 = userService.getUserByUsername("admin");
    }


    @org.junit.jupiter.api.Test
    void add() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUsername("admin_" + i);
            user.setPassword("admin_password_" + i);

            Role role = new Role();
            role.setId(1);
            user.setRole(role);
            user.setCreateTime(DateUtil.getNow());
            user.setUpdateTime(DateUtil.getNow());
            userService.addUser(user);
        }
    }

    @org.junit.jupiter.api.Test
    void delete() {
        userService.deleteUser(1002);
    }

    @org.junit.jupiter.api.Test
    void update() {
        User user = new User();
        user.setId(11);
        user.setUsername("update" );
        user.setPassword("update");
        Role role = new Role();
        role.setId(2);
        user.setRole(role);
        user.setUpdateTime(DateUtil.getNow());
        userService.updateUsers(user);
    }


}