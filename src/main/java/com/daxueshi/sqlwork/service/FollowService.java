package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.Follow;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-29 -15:17
 */
public interface FollowService {
    List<Follow> findIFollowWho(String email);

    List<Follow> findWhoFollowMe(String email);

    void cancelFollow(String followingEmail, String followedEmail);

    void addFollow(String followingEmail, String followedEmail);
}
