package com.daxueshi.sqlwork.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author onion
 * @date 2019-04-08 -20:51
 */
@ApiModel("毕业生实体")
public class Graduate implements Serializable {


    private static final long serialVersionUID = -6358879932920351127L;

    private String userId;
    private Integer universityId;
    private Integer majorId;
    private Integer companyId;
    @ApiModelProperty("积分")
    private Integer score;
    private Double salary;
    @ApiModelProperty("职位")
    private String position;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Integer universityId) {
        this.universityId = universityId;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
