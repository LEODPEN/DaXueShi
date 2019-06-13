package com.daxueshi.sqlwork.service;

import com.github.pagehelper.PageInfo;

/**
 * @author onion
 * @date 2019-05-29 -15:17
 */
public interface FollowService {
    PageInfo findIFollowWho(String email, Integer page, Integer size);

    PageInfo findWhoFollowMe(String email, Integer page, Integer size);

    void cancelFollow(String followingEmail, String followedEmail);

    void addFollow(String followingEmail, String followedEmail);
}
