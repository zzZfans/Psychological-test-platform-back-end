package com.cqjtu.mindassess.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.mapper.UserMapper;
import com.cqjtu.mindassess.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public User getUserByUsername(String username) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("username",username);
        return userMapper.selectOne(qw);
    }
}
