package com.daxueshi.sqlwork.domain;

import java.io.Serializable;

/**
 * @author onion
 * @date 2019-04-08 -20:54
 */
public class Company implements Serializable {


    private static final long serialVersionUID = -5693815303324276770L;

    private Integer companyId;
    private String companyName;
    private String city;
    private String type;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
