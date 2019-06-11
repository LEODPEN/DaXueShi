package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.dao.MessageDao;
import com.daxueshi.sqlwork.domain.Message;
import com.daxueshi.sqlwork.service.MessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author onion
 * @date 2019-06-11 -11:06
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    @Override
    public void saveMessage(String followedEmail, String message, Date date) {
        messageDao.saveMessage(followedEmail, message, date);
    }

    @Override
    public PageInfo <Message> findMyMessage(String email, Integer page, Integer size) {
        String sort = "publish desc";
        PageHelper.startPage(page, size, sort);
        List<Message> messageList = messageDao.findMessages(email, page, size);
        return new PageInfo<>(messageList);
    }

    @Override
    public void handleAllMessages(String email){
        messageDao.handleAllMessages(email);
    }

    @Override
    public void handleSelectMessages(List<Integer> idList) {
        messageDao.handleSelectMessages(idList);
    }

    @Override
    public void deleteAllMessage(String email) {
        messageDao.deleteAllMessage(email);
    }

    @Override
    public PageInfo<Message> findAllMessages(String email, Integer page, Integer size) {
        String sort = "publish desc";
        PageHelper.startPage(page, size, sort);
        List<Message> messageList = messageDao.findAllMessages(email);
        return new PageInfo<>(messageList);
    }

    @Override
    public void deleteSelectMessages(List<Integer> idList) {
        messageDao.deleteSelectMessages(idList);
    }


}
