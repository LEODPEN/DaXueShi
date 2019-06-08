package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.domain.Follow;
import com.daxueshi.sqlwork.service.FollowService;
import com.daxueshi.sqlwork.utils.ResultUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author onion
 * @date 2019-05-29 -14:54
 */
@RestController
@Api(tags = "关注请求")
//其实只有学生能操作？不如放在student？
@RequestMapping("/dxs/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @GetMapping("/getInfo")
    @ApiOperation("查询我关注的信息")
    public Result findFollowInfos(@RequestParam String email){
        Map<String, Object> followInfos = new HashMap<>();
        PageInfo following = followService.findIFollowWho(email);
        PageInfo followed = followService.findWhoFollowMe(email);
        followInfos.put("following",following);
        followInfos.put("followingCnt",following.getTotal());
        followInfos.put("followed",followed);
        followInfos.put("followedCnt",followed.getTotal());
        return ResultUtils.success(followInfos);
    }

    @DeleteMapping("/cancelFollow")
    @ApiOperation("取消关注")
    public Result cancelFollow(@RequestParam String followingEmail, @RequestParam String followedEmail){
        followService.cancelFollow(followingEmail,followedEmail);
        return ResultUtils.success();
    }

    //如果已经关注？
    @PostMapping("/addFollow")
    @ApiOperation("添加关注")
    public Result addFollow(@RequestParam String followingEmail, @RequestParam String followedEmail){
        followService.addFollow(followingEmail,followedEmail);
        return ResultUtils.success();
    }
}
