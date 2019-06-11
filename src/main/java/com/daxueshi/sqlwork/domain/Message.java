package com.daxueshi.sqlwork.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author onion
 * @date 2019-06-11 -10:40
 */
@Data
public class Message {
    private Integer id;
    private String email;
    private String message;
    private Date publish;
    private Boolean handle;
}
