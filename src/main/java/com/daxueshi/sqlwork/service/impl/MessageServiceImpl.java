package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.dao.MessageDao;
import com.daxueshi.sqlwork.domain.Message;
import com.daxueshi.sqlwork.service.MessageService;
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
    public void saveMessage(String followedEmail, StringBuilder message, Date date) {
        messageDao.saveMessage(followedEmail, message.toString(), date);
    }

    @Override
    public List<Message> findMyMessage(String email) {
        return messageDao.findMessages(email);
    }

    @Override
    public void handleMessage(Integer id) {
        messageDao.handleMessage(id);
    }

    @Override
    public void deleteAllMessage(String email) {
        messageDao.deleteAllMessage(email);
    }


}
