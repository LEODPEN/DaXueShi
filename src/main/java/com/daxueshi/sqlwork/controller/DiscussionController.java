package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.domain.Discussion;
import com.daxueshi.sqlwork.service.DiscussionService;
import com.daxueshi.sqlwork.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * @author onion
 * @date 2019-05-10 -09:39
 */
@RestController
@RequestMapping("/api/v1")
public class DiscussionController implements Serializable {
    @Autowired
    private DiscussionService discussionService;

    @GetMapping("/discussions")
    public Result findAll(){
        return ResultUtils.success(discussionService.findAll());
    }
    @GetMapping("/discussions/{id}")
    public Result findById(@PathVariable String id){
        return ResultUtils.success(discussionService.findById(id));
    }

    @PostMapping("/discussions")
    public Result save(@RequestBody Discussion discussion){
        discussionService.save(discussion);
        return ResultUtils.success();
    }

    @PutMapping("/discussions/{id}")
    public Result update(@PathVariable String id, @RequestBody Discussion discussion){
        discussionService.update(discussion);
        return ResultUtils.success();
    }

    @DeleteMapping("/discussions/{id}")
    public Result deleteById(@PathVariable String id){
        discussionService.deleteById(id);
        return ResultUtils.success();
    }

    @GetMapping("/discussions/comments/{id}/{page}/{size}")
    public Result findByParentId(@PathVariable String id,@PathVariable Integer page,@PathVariable Integer size){
        return ResultUtils.success(discussionService.findByParentId(id,page,size));
    }
}
