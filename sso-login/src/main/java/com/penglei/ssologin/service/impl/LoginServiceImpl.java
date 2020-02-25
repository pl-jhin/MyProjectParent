package com.penglei.ssologin.service.impl;

import cn.penglei.mapper.Mapper.UserMapper;
import cn.penglei.pojo.pojo.User;
import com.penglei.ssologin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User checkLogin(User user) {
        User loginCheck = userMapper.loginCheck(user);

        if (loginCheck!=null){
            return loginCheck;
        }else {
            return null;
        }
    }

    @Override
    public boolean checkUserNumber(User user) {
        int i = userMapper.findUserCheck(user);
        if (i>0) return false;
        else return true;
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }
}
