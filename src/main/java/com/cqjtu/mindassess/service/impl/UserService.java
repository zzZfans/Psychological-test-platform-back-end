package com.cqjtu.mindassess.service.impl;

import com.cqjtu.mindassess.pojo.vo.UserReq;
import com.cqjtu.mindassess.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;


/**
 * @author zhangning
 */

@Service
public class UserService implements IUserService {


    // mock
    private static HashMap<String,String> mysql;
    static {
        mysql = new HashMap<>();
        mysql.put("zs","1");
        mysql.put("ls","1");
        mysql.put("ww","1");
    }

    @Override
    public boolean usernamePasswordLogin(String username, String password) {
        return false;
    }

    @Override
    public boolean phoneNumberLogin(String phoneNumber, String code) {
        return false;
    }

    @Override
    public boolean emailLogin(String email, String code) {
        return false;
    }

    @Override
    public void login(UserReq user) {
        final int method = user.getLoginMethod();
        if( method == 1){
            //TODO
        }
    }
}
