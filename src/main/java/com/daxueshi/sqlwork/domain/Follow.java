package com.daxueshi.sqlwork.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author onion
 * @date 2019-05-29 -15:03
 */
@ApiModel("关注")
@Data
public class Follow implements Serializable {
    private String followingEmail;
    private String followedEmail;
    private Integer times;
    private String followingProfile;
    private String followedProfile;
}
