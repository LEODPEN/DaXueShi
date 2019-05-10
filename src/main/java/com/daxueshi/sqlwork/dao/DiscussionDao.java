package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.Discussion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author onion
 * @date 2019-05-10 -09:31
 */
@Repository
public interface DiscussionDao extends MongoRepository<Discussion, String> {
    public Page<Discussion> findByParentId(String parentId, Pageable pageable);
}
