package com.cqjtu.mindassess.service;

import com.cqjtu.mindassess.entity.User;

public interface IUserService {

    User getUserByUsername(String username);
}
