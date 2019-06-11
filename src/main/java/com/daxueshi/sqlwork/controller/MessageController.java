package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.service.MessageService;
import com.daxueshi.sqlwork.utils.ResultUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author onion
 * @date 2019-06-11 -11:13
 */
@RestController
@RequestMapping("/dxs/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping
    public Result findMyMessage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam String email){
        PageInfo pageInfo = messageService.findMyMessage(email, page, size);
        return ResultUtils.success(pageInfo);
    }

    @GetMapping("/all")
    public Result findAllMessages(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam String email){
        PageInfo pageInfo = messageService.findAllMessages(email, page, size);
        return ResultUtils.success(pageInfo);
    }

    @PutMapping
    public Result handleSelectMessage(@RequestParam String id){
        String[] split = id.split("&");
        List<Integer> idList = new ArrayList<>();
        for(String s : split){
            idList.add(Integer.parseInt(s));
        }
        messageService.handleSelectMessages(idList);
        return ResultUtils.success();
    }

    @PutMapping("/all")
    public Result handleAllMessages(@RequestParam String email){
        messageService.handleAllMessages(email);
        return ResultUtils.success();
    }

    @DeleteMapping("/all")
    public Result deleteAllMessages(@RequestParam String email){
        messageService.deleteAllMessage(email);
        return ResultUtils.success();
    }

    @DeleteMapping
    public Result deleteSelectMessages(@RequestParam String id){
        String[] split = id.split("&");
        List<Integer> idList = new ArrayList<>();
        for(String s : split){
            idList.add(Integer.parseInt(s));
        }
        messageService.deleteSelectMessages(idList);
        return ResultUtils.success();
    }


}
