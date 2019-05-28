package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.Discussion;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-11 -10:15
 */
public interface DiscussionService {
    List<Discussion> findAll();

    Discussion findById(String id);

    void save(Discussion discussion);

    void update(Discussion discussion);

    void deleteById(String id);

    Page<Discussion> findByParentId(String parentId, int page, int size);

    void updateVisit(String id);
}
