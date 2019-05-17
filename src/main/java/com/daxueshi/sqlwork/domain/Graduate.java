package com.daxueshi.sqlwork.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author onion
 * @date 2019-04-08 -20:51
 */
@ApiModel("毕业生实体")
@Getter
@Setter
public class Graduate implements Serializable {

    private static final long serialVersionUID = -6358879932920351127L;
    private String userId;
    private Integer universityId;
    private Integer majorId;
    private Integer companyId;
    @ApiModelProperty("积分")
    private Integer scores;
    private Double salary;
    @ApiModelProperty("职位")
    private String position;

    @Override
    public String toString() {
        return "Graduate{" +
                "userId='" + userId + '\'' +
                ", universityId=" + universityId +
                ", majorId=" + majorId +
                ", companyId=" + companyId +
                ", scores=" + scores +
                ", salary=" + salary +
                ", position='" + position + '\'' +
                '}';
    }
}
