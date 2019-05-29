package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.dao.CommentDao;
import com.daxueshi.sqlwork.dao.DiscussionDao;
import com.daxueshi.sqlwork.dao.FollowDao;
import com.daxueshi.sqlwork.domain.Comment;
import com.daxueshi.sqlwork.domain.Discussion;
import com.daxueshi.sqlwork.service.DiscussionService;
import com.daxueshi.sqlwork.utils.KeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author onion
 * @date 2019-05-10 -09:33
 */
@Service
public class DiscussionServiceImpl implements DiscussionService {
    @Autowired
    private DiscussionDao discussionDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private FollowDao followDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Page findAll(Pageable pageable){
        return discussionDao.findAll(pageable);
    }

    @Override
    public Page<Discussion> findByFollow(String email, Pageable pageable) {
        List<String> followedEmail = followDao.findFollowedEmail(email);
        return discussionDao.findByEmailIn(followedEmail, pageable);
    }

    @Override
    public Page<Discussion> findByEmail(String email, Pageable pageable) {
        return discussionDao.findByEmail(email, pageable);
    }

    public Discussion findById(String id){
        return discussionDao.findById(id).get();
    }

    public void save(Discussion discussion){
        //指定Id
        discussion.setId(KeyUtils.genUniqueKey());
        discussion.setPublishTime(new Date());
        discussion.setLastEditTime(new Date());
        discussion.setVisits(0);
        discussion.setComments(0);
        discussion.setThumbs(0);
        discussionDao.save(discussion);
    }

    public void update(Discussion discussion){
        discussion.setLastEditTime(new Date());
        discussionDao.save(discussion);
    }

    public void deleteById(String id){

        discussionDao.deleteById(id);
    }



    public void makeComment(Comment comment){
        String id = KeyUtils.genUniqueKey();
        comment.setCommentId(id);
        comment.setLastEditTime(new Date());
        commentDao.save(comment);
        updateCount(id,"comments");
        updateCount(id,"visits");
    }

    public void updateCount(String id, String key){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc(key,1);
        mongoTemplate.updateFirst(query,update,"discussion");
    }

}
