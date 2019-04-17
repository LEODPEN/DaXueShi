package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.User;

import java.util.List;

public interface UserService {
    User saveWeChatUser(String code);
    List<User> findAll();
}
