package com.daxueshi.sqlwork.domain;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author onion
 * @date 2019-04-08 -20:19
 */
@ApiModel("专业实体")
@Getter
@Setter
public class Major implements Serializable {


    private static final long serialVersionUID = -8043169636833484339L;
    private Integer majorId;
    private String majorName;
    private String description;

    @Override
    public String toString() {
        return "Major{" +
                "majorId=" + majorId +
                ", majorName='" + majorName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
