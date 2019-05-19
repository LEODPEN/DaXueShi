package com.daxueshi.sqlwork.domain;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author onion
 * @date 2019-04-08 -20:54
 */
@ApiModel("公司实体")
@Getter
@Setter
public class Company implements Serializable {
    private static final long serialVersionUID = -5693815303324276770L;
    private Integer companyId;
    private String companyName;
    private String city;
    private String type;
    private String description;

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", city='" + city + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
