package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.Discussion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-10 -09:31
 */
@Repository
public interface DiscussionDao extends MongoRepository<Discussion, String> {
    Page<Discussion> findByMajorName(String majorName, Pageable pageable);
    Page<Discussion> findByEmail(String email, Pageable pageable);
    Page<Discussion> findByEmailIn(List<String> emails, Pageable pageable);
}
