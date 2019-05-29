package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.domain.Comment;
import com.daxueshi.sqlwork.domain.Discussion;
import com.daxueshi.sqlwork.service.impl.DiscussionServiceImpl;
import com.daxueshi.sqlwork.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

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


    @ApiOperation("查询所有帖子(根据property排序)")
    @GetMapping("/all")
    public Result findAll(
            @RequestParam(value = "page", defaultValue = "1")Integer page,
            @RequestParam(value = "size", defaultValue = "20")Integer size,
            @RequestParam(value = "sort", defaultValue = "publishTime")String property){
        Sort sort = new Sort(Sort.Direction.DESC,property);
        Pageable pageable  = PageRequest.of(page - 1, size, sort);
        Page pageInfo = discussionService.findAll(pageable);
        return ResultUtils.success(pageInfo);
    }

    @ApiOperation("查询我所关注的人发表的帖子")
    @GetMapping("/follow")
    @ApiImplicitParam(name = "email",value = "我的邮箱")
    public Result findByFollow(@RequestParam(value = "page", defaultValue = "1")Integer page,
                               @RequestParam(value = "size", defaultValue = "20")Integer size,
                               @RequestParam String email){
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Discussion> pageInfo = discussionService.findByFollow(email, pageable);
        return ResultUtils.success(pageInfo);
    }

    @ApiOperation("查询某用户发表的所有帖子")
    @GetMapping
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


    @ApiOperation("保存帖子")
    @PostMapping
    public Result save(@RequestBody Discussion discussion){
        discussionService.save(discussion);
        return ResultUtils.success();
    }

    @ApiOperation("更改帖子")
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

    @ApiOperation("发表评论")
    @PostMapping("/comment")
    public Result makeComment(@RequestBody Comment comment){
        discussionService.makeComment(comment);
        return ResultUtils.success();
    }
    @ApiOperation("查询帖子的评论")
    @GetMapping
    public Result findComments(){
        return null;
    }
}
