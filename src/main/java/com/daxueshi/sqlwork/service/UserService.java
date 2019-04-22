package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.User;

import java.util.List;

public interface UserService {
    User saveWeChatUser(String code);
    List<User> findAll();
    User login(String email,String password);
    boolean activate(String userId,int status);
    boolean register(User user);
}
