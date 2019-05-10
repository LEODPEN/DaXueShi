package com.daxueshi.sqlwork.service;

import com.daxueshi.sqlwork.dao.DiscussionDao;
import com.daxueshi.sqlwork.domain.Discussion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class DiscussionService {
    @Autowired
    private DiscussionDao discussionDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Discussion> findAll(){
        return discussionDao.findAll();
    }

    public Discussion findById(String id){
        return discussionDao.findById(id).get();
    }

    public void save(Discussion discussion){
        //指定Id
        discussion.setPublishTime(new Date());
        discussion.setVisits(0);
        discussion.setComment(0);
        discussion.setState("1");
        //如果当前恢复有父节点，父节点吐槽数加1
        if(discussion.getParentId()!=null && !"".equals(discussion.getParentId())){
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(discussion.getParentId()));
            Update update = new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"discussion");
        }
        discussionDao.save(discussion);
    }

    public void update(Discussion discussion){
        discussionDao.save(discussion);
    }

    public void deleteById(String id){
        discussionDao.deleteById(id);
    }

    public Page<Discussion> findByParentId(String parentId, int page, int size){
        Pageable pageable = PageRequest.of(page-1, size);
        return discussionDao.findByParentId(parentId,pageable);
    }

    public void updateVisit(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("visit",1);
        mongoTemplate.updateFirst(query,update,"discussion");
    }
}
