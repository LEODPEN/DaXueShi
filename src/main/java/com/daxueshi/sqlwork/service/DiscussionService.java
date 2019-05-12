package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.domain.Discussion;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-11 -10:15
 */
public interface DiscussionService {
    public List<Discussion> findAll();

    public Discussion findById(String id);

    public void save(Discussion discussion);

    public void update(Discussion discussion);

    public void deleteById(String id);

    public Page<Discussion> findByParentId(String parentId, int page, int size);

    public void updateVisit(String id);
}
