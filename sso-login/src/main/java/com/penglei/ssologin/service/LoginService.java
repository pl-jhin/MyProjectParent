package com.penglei.ssologin.service;

import cn.penglei.pojo.pojo.User;

public interface LoginService {
    User checkLogin(User user);

    boolean checkUserNumber(User user);

    void insertUser(User user);
}
