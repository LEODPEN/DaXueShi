package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.Discussion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author onion
 * @date 2019-05-11 -10:15
 */
public interface DiscussionService {
    Page findAll(Pageable pageable);

    Page<Discussion> findByFollow(String email, Pageable pageable);

    Page<Discussion> findByEmail(String email, Pageable pageable);

    Discussion findById(String id);

    void save(Discussion discussion);

    void update(Discussion discussion);

    void deleteById(String id);


}
