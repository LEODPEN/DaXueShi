package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.Message;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

/**
 * @author onion
 * @date 2019-06-11 -11:06
 */
public interface MessageService {
    void saveMessage(String followedEmail, String message, Date date);

    PageInfo <Message> findMyMessage(String email, Integer page, Integer size);

    PageInfo <Message> findAllMessages(String email, Integer page, Integer size);

    void deleteSelectMessages(List<Integer> idList);

    void deleteAllMessage(String email);

    void handleSelectMessages(List<Integer> idList);

    void handleAllMessages(String email);
}
