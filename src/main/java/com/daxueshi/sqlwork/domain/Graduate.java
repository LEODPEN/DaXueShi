package com.daxueshi.sqlwork.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author onion
 * @date 2019-04-08 -20:51
 */
@ApiModel("毕业生实体")
@Data
public class Graduate implements Serializable {

    private static final long serialVersionUID = -6358879932920351127L;
    private String email;
    private String universityName;
    private String majorName;
    private String companyName;
    @ApiModelProperty("积分")
    private Integer scores;
    private Integer salary;
    @ApiModelProperty("职位")
    private String position;
    private Integer graduateYear;
    private Integer status;
}
