package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.dao.FollowDao;
import com.daxueshi.sqlwork.domain.Follow;
import com.daxueshi.sqlwork.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-29 -15:18
 */
@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowDao followDao;

    @Override
    public List<Follow> findIFollowWho(String email) {
        return followDao.findIFollowWho(email);
    }

    @Override
    public List<Follow> findWhoFollowMe(String email) {
        return followDao.findWhoFollowMe(email);
    }

    @Override
    public void cancelFollow(String followingEmail, String followedEmail) {
        followDao.cancelFollow(followingEmail, followedEmail);
    }

    @Override
    public void addFollow(String followingEmail, String followedEmail) {
        followDao.addFollow(followingEmail, followedEmail);
    }
}
