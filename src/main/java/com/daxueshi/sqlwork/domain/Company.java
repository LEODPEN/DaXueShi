package com.daxueshi.sqlwork.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author onion
 * @date 2019-04-08 -20:54
 */
@ApiModel("公司实体")
@Data
public class Company implements Serializable {
    private static final long serialVersionUID = -5693815303324276770L;
    private String companyName;
    private String address;
    private String keyword;
    private String description;
}
