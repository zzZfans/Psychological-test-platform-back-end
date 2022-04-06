package com.cqjtu.mindassess.mapper;

import com.cqjtu.mindassess.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;
    @Test
    void test(){
        User user = userMapper.selectById(1);
        System.out.println(user);
    }
}