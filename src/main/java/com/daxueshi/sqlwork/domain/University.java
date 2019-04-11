package com.daxueshi.sqlwork.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @author onion
 * @date 2019-04-08 -20:15
 */
@Component
public class University implements Serializable {
    private Integer universityId;
    private String universityName;
    private List<Major> majors;

    public Integer getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Integer universityId) {
        this.universityId = universityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public List<Major> getMajors() {
        return majors;
    }

    public void setMajors(List<Major> majors) {
        this.majors = majors;
    }


}