package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.domain.Message;
import com.daxueshi.sqlwork.service.MessageService;
import com.daxueshi.sqlwork.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result findMyMessage(@RequestParam String email){
        List<Message> messageList = messageService.findMyMessage(email);
        return ResultUtils.success(messageList);
    }

    @PutMapping
    public Result handleMessage(@RequestParam Integer id){
        messageService.handleMessage(id);
        return ResultUtils.success();
    }

    @DeleteMapping
    public Result deleteAllMessage(@RequestParam String email){
        messageService.deleteAllMessage(email);
        return ResultUtils.success();
    }


}
