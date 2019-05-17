package com.daxueshi.sqlwork.domain;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author onion
 * @date 2019-04-08 -20:49
 */
@Getter
@Setter
@ApiModel("学生实体")
public class Student implements Serializable {


    private static final long serialVersionUID = -7311090085844484220L;
    private String userId;
    private Integer universityId;
    private Integer majorId;
    private Integer score;

    @Override
    public String toString() {
        return "Student{" +
                "userId='" + userId + '\'' +
                ", universityId=" + universityId +
                ", majorId=" + majorId +
                ", score=" + score +
                '}';
    }
}
