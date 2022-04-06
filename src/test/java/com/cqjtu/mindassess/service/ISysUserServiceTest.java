package com.cqjtu.mindassess.service;

import com.cqjtu.mindassess.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ISysUserServiceTest {

    @Resource
    ISysUserService sysUserService;

    @Test
    void queryUserByUsernameOrPhoneNumber() {
        List<User> users = sysUserService.queryUserByUsernameOrPhoneNumber("a", "a");
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println(users.size());
    }

}