package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.User;

public interface UserService {
    User login(String email,String password);
    void register(User user,String checkcode);
    User findByEmail(String email);
    void updateUser(User user);
    void deleteByEmail(String email);
    void sendCheckcode(String email);
    void save(User user);
}
