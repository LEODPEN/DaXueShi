package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.dao.CommentDao;
import com.daxueshi.sqlwork.dao.DiscussionDao;
import com.daxueshi.sqlwork.dao.FollowDao;
import com.daxueshi.sqlwork.domain.Comment;
import com.daxueshi.sqlwork.domain.Discussion;
import com.daxueshi.sqlwork.dto.CommentDTO;
import com.daxueshi.sqlwork.service.DiscussionService;
import com.daxueshi.sqlwork.utils.JwtUtils;
import com.daxueshi.sqlwork.utils.KeyUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Page findAll(Pageable pageable, String majorName){
        return discussionDao.findByMajorName(majorName, pageable);
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

    @Override
    public Discussion findById(String id){
        updateCount(id,"visits");
        return discussionDao.findById(id).get();
    }

    @Override
    public void save(Discussion discussion,String token){
        String email = (String) JwtUtils.parseJwt(token).get("email");
        String nickname = (String) JwtUtils.parseJwt(token).get("name");
        discussion.setEmail(email);
        discussion.setNickname(nickname);
        discussion.setId(KeyUtils.genUniqueKey());
        discussion.setPublishTime(new Date());
        discussion.setLastEditTime(new Date());
        discussion.setVisits(0);
        discussion.setComments(0);
        discussion.setThumbs(0);
        discussionDao.save(discussion);
    }

    @Override
    public Page findByKeyword(Pageable pageable, String majorName, String keyword) {
        return discussionDao.findByMajorNameAndTitleLike(majorName, keyword, pageable);
    }

    @Override
    public void update(Discussion discussion){
        Discussion oldDiscussion = discussionDao.findById(discussion.getId()).get();
        discussion.setNickname(oldDiscussion.getNickname());
        discussion.setMajorName(oldDiscussion.getMajorName());
        discussion.setEmail(oldDiscussion.getEmail());
        discussion.setPublishTime(oldDiscussion.getPublishTime());
        discussion.setVisits(oldDiscussion.getVisits());
        discussion.setThumbs(oldDiscussion.getThumbs());
        discussion.setComments(oldDiscussion.getComments());
        discussion.setLastEditTime(new Date());
        discussionDao.save(discussion);
    }

    @Override
    public void deleteById(String id){
        discussionDao.deleteById(id);
        commentDao.deleteByDiscussionId(id);
    }

    @Override
    public void makeComment(Comment comment){
        String id = comment.getDiscussionId();

        Discussion discussion = discussionDao.findById(id).get();
        discussion.setLastEditTime(new Date());
        discussion.setVisits(discussion.getVisits() + 1);
        discussion.setComments(discussion.getComments() + 1);
        discussionDao.save(discussion);

        comment.setCommentId(KeyUtils.genUniqueKey());
        comment.setLastEditTime(new Date());
        commentDao.save(comment);

    }

    @Override
    public PageInfo findComments(String id,Integer page, Integer size) {
        String order = "last_edit_time desc";
        PageHelper.startPage(page, size, order);
        List<Comment> comments = commentDao.findById(id);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for(Comment comment: comments){
            String commentId = comment.getCommentId();
            List<Comment> childrenList = commentDao.findChildrenById(commentId);
            String content = comment.getContent();
            String email = comment.getEmail();
            Date lastEditTime = comment.getLastEditTime();
            String nickname = comment.getNickname();
            commentDTOList.add(new CommentDTO(childrenList,commentId,content,email,lastEditTime,nickname));
        }
        PageInfo pageInfo = new PageInfo(comments);
        pageInfo.setTotal(pageInfo.getTotal());
        pageInfo.setList(commentDTOList);
        return pageInfo;
    }

    @Override
    public void deleteComment(String discussionId, String commentId) {
        commentDao.deleteCertainComment(discussionId, commentId);
    }

    @Override
    public void deleteCommentByCommentId(String commentId) {
        commentDao.deleteCommentByCommentId(commentId);
    }

    @Override
    public void updateComment(Comment comment) {
        comment.setLastEditTime(new Date());
        commentDao.update(comment);
    }


    public void updateCount(String id, String key){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc(key,1);
        mongoTemplate.updateFirst(query,update,"discussions");
    }

}
