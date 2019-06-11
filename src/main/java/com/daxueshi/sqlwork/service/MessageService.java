package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.Message;

import java.util.Date;
import java.util.List;

/**
 * @author onion
 * @date 2019-06-11 -11:06
 */
public interface MessageService {
    void saveMessage(String followedEmail, StringBuilder message, Date date);

    List<Message> findMyMessage(String email);

    void handleMessage(Integer id);

    void deleteAllMessage(String email);
}
