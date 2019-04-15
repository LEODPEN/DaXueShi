package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.User;

public interface UserService {
    User saveWeChatUser(String code);
}
