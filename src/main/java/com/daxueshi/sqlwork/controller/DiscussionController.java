package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.dao.FollowDao;
import com.daxueshi.sqlwork.dao.MessageDao;
import com.daxueshi.sqlwork.domain.Comment;
import com.daxueshi.sqlwork.domain.Discussion;
import com.daxueshi.sqlwork.domain.Follow;
import com.daxueshi.sqlwork.service.impl.DiscussionServiceImpl;
import com.daxueshi.sqlwork.socket.MyWebSocket;
import com.daxueshi.sqlwork.utils.JwtUtils;
import com.daxueshi.sqlwork.utils.ResultUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author onion
 * @date 2019-05-10 -09:39
 */
@Api(tags = "论坛模块相关请求")
@RestController
@RequestMapping("/dxs/discussion")
public class DiscussionController {

    @Autowired
    private DiscussionServiceImpl discussionService;

    @Autowired
    private FollowDao followDao;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private MyWebSocket myWebSocket;

    // 查询功能
    @ApiOperation("查询所有帖子(根据property排序)")
    @GetMapping("/all")
    public Result findAll(
            @RequestParam(value = "page", defaultValue = "1")Integer page,
            @RequestParam(value = "size", defaultValue = "5")Integer size,
            @RequestParam(value = "property", defaultValue = "publishTime")String property,
            @RequestParam String majorName,
            @RequestParam(defaultValue = "")String keyword){
        Sort sort = new Sort(Sort.Direction.DESC,property);
        Pageable pageable  = PageRequest.of(page - 1, size, sort);
        Page pageInfo;
        if(keyword.equals("")){
            pageInfo = discussionService.findAll(pageable, majorName);
        }else{
            pageInfo = discussionService.findByKeyword(pageable, majorName, keyword);
        }
        return ResultUtils.success(pageInfo);
    }

    @ApiOperation("查询我所关注的人发表的帖子")
    @GetMapping("/follow")
    @ApiImplicitParam(name = "email",value = "我的邮箱")
    public Result findByFollow(@RequestParam(value = "page", defaultValue = "1")Integer page,
                               @RequestParam(value = "size", defaultValue = "5")Integer size,
                               @RequestParam String email){
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Discussion> pageInfo = discussionService.findByFollow(email, pageable);
        return ResultUtils.success(pageInfo);
    }

    @ApiOperation("查询我发布的帖子")
    @GetMapping("/mine")
    public Result findMine(@RequestParam(value = "page", defaultValue = "1")Integer page,
                           @RequestParam(value = "size", defaultValue = "5")Integer size,
                           @RequestParam String email){
        Sort sort = new Sort(Sort.Direction.DESC, "publishTime");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Discussion> pageInfo = discussionService.findByEmail(email, pageable);
        return ResultUtils.success(pageInfo);
    }

//    @ApiOperation("通过标题匹配查询")
//    @GetMapping("/search")
//    public Result findBySearch(@RequestParam(value = "page", defaultValue = "1")Integer page,
//                               @RequestParam(value = "size", defaultValue = "5")Integer size,
//                               @RequestParam String title){
//
//        return ResultUtils.success();
//    }

    @ApiOperation("查询某用户发表的所有帖子")
    @GetMapping("/publish")
    @ApiImplicitParam(name = "email",value = "他人的邮箱")
    public Result findByEmail(@RequestParam(value = "page", defaultValue = "1")Integer page,
                              @RequestParam(value = "size", defaultValue = "20")Integer size,
                              @RequestParam String email){
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Discussion> discussions = discussionService.findByEmail(email, pageable);
        return ResultUtils.success(discussions);
    }

    @ApiOperation("指定帖子Id查询")
    @GetMapping
    public Result findById(@RequestParam("id") String id){
        return ResultUtils.success(discussionService.findById(id));
    }

    // 增删改功能
    @ApiOperation("保存帖子")
    @PostMapping
    public Result save(@RequestBody Discussion discussion,@RequestParam String token){
        discussionService.save(discussion,token);
        String email = (String) JwtUtils.parseJwt(token).get("email");
        List<Follow> followList = followDao.findWhoFollowMe(email);
        String message = email + "发布了有关 " + discussion.getTitle() + " 的讨论";
        for(Follow follow : followList){
            myWebSocket.sendOneMessage(follow.getFollowingEmail(), message);
        }
        return ResultUtils.success();
    }

    @ApiOperation("编辑帖子")
    @PutMapping
    public Result update(@RequestBody Discussion discussion){
        discussionService.update(discussion);
        return ResultUtils.success();
    }

    @ApiOperation("删除帖子")
    @DeleteMapping
    public Result deleteById(@RequestParam String id){
        discussionService.deleteById(id);
        return ResultUtils.success();
    }

    // 其他功能
    @ApiOperation("点赞")
    @PutMapping("/thumb")
    public Result makeThumb(@RequestParam String id){
        discussionService.updateCount(id,"thumbs");
        return ResultUtils.success();
    }

    @ApiOperation("发表评论")
    @PostMapping("/comment")
    public Result makeComment(@RequestBody Comment comment){
        discussionService.makeComment(comment);
        Discussion discussion = discussionService.findById(comment.getDiscussionId());
        if(!discussion.getEmail().equals(comment.getEmail())) {
            String message = comment.getNickname() + "对您的 " + discussion.getTitle() + "有了新回复";
            messageDao.saveMessage(discussion.getEmail(), message, new Date());
            myWebSocket.sendOneMessage(discussion.getEmail(), message);
        }
        return ResultUtils.success();
    }

    @ApiOperation("查询帖子的评论")
    @GetMapping("/comment")
    public Result findComments(@RequestParam String id,
                               @RequestParam(defaultValue = "0") Integer page,
                               @RequestParam(defaultValue = "5") Integer size){
        PageInfo pageInfo = discussionService.findComments(id, page, size);
        return ResultUtils.success(pageInfo);
    }

    @ApiOperation("删除评论")
    @DeleteMapping("/comment")
    public Result deleteComment(@RequestParam String commentId){
        discussionService.deleteCommentByCommentId(commentId);
        return ResultUtils.success();
    }


    @ApiOperation("编辑评论")
    @PutMapping("/comment")
    public Result updateComment(@RequestBody Comment comment){
        discussionService.updateComment(comment);
        return ResultUtils.success();
    }




}
