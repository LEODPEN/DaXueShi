package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.domain.Discussion;
import com.daxueshi.sqlwork.service.DiscussionService;
import com.daxueshi.sqlwork.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * @author onion
 * @date 2019-05-10 -09:39
 */
@Api(tags = "论坛模块相关请求")
@RestController
@RequestMapping("/api/v1")
public class DiscussionController implements Serializable {
    @Autowired
    private DiscussionService discussionService;

    @ApiOperation("查询所有帖子")
    @GetMapping("/discussions")
    public Result findAll(){
        return ResultUtils.success(discussionService.findAll());
    }

    @ApiOperation("指定帖子Id查询")
    @GetMapping("/discussions/{id}")
    public Result findById(@PathVariable String id){
        return ResultUtils.success(discussionService.findById(id));
    }

    @ApiOperation("保存帖子")
    @PostMapping("/discussions")
    public Result save(@RequestBody Discussion discussion){
        discussionService.save(discussion);
        return ResultUtils.success();
    }

    @ApiOperation("更改帖子")
    @PutMapping("/discussions/{id}")
    public Result update(@PathVariable String id, @RequestBody Discussion discussion){
        discussionService.update(discussion);
        return ResultUtils.success();
    }

    @ApiOperation("删除帖子")
    @DeleteMapping("/discussions/{id}")
    public Result deleteById(@PathVariable String id){
        discussionService.deleteById(id);
        return ResultUtils.success();
    }

    @ApiOperation("根据上级用户Id查询相关帖子")
    @GetMapping("/discussions/comments/{id}/{page}/{size}")
    public Result findByParentId(@PathVariable String id,@PathVariable Integer page,@PathVariable Integer size){
        return ResultUtils.success(discussionService.findByParentId(id,page,size));
    }
}
