package com.cqjtu.mindassess.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleMapperTest {

    @Autowired
    RoleMapper roleMapper;

    @Test
    void listRoleInfo() {
        roleMapper.listRoleInfo().stream().forEach(System.out::println);
    }
}