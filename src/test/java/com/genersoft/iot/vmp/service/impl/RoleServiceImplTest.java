package com.genersoft.iot.vmp.service.impl;

import com.genersoft.iot.vmp.service.IRoleService;
import com.genersoft.iot.vmp.storager.dao.dto.Role;
import com.genersoft.iot.vmp.utils.DateUtil;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;


@SpringBootTest
class RoleServiceImplTest {

    @Resource
    private IRoleService roleService;

    @org.junit.jupiter.api.Test
    void getAllUser() {
        List<Role> all = roleService.getAll();
        Role roleById = roleService.getRoleById(1);

    }


    @org.junit.jupiter.api.Test
    void add() {
        for (int i = 0; i < 10; i++) {
            Role role = new Role();
            role.setName("test+" + i);
            role.setAuthority("adadadda");
            role.setCreateTime(DateUtil.getNow());
            role.setUpdateTime(DateUtil.getNow());
            roleService.add(role);
        }
    }

    @org.junit.jupiter.api.Test
    void delete() {
        roleService.delete(20);
    }

    @org.junit.jupiter.api.Test
    void update() {
        Role role = new Role();
        role.setId(21);
        role.setName("TTTTTT");
        role.setAuthority("adadadda");
        roleService.update(role);
    }
}