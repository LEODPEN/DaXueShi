package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.dao.FollowDao;
import com.daxueshi.sqlwork.domain.Follow;
import com.daxueshi.sqlwork.service.FollowService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PageInfo findIFollowWho(String email) {
//        String order = "times desc";
        PageHelper.startPage(0,10);
        List<Follow> followList = followDao.findIFollowWho(email);

        return new PageInfo(followList);
    }

    @Override
    public PageInfo findWhoFollowMe(String email) {
        PageHelper.startPage(0,10);
        List<Follow> followList = followDao.findWhoFollowMe(email);

        return new PageInfo(followList);
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
