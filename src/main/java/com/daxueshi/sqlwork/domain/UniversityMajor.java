package com.daxueshi.sqlwork.domain;

import java.io.Serializable;

/**
 * @author onion
 * @date 2019-04-08 -20:58
 */
public class UniversityMajor implements Serializable {
    private Integer universityId;
    private Integer majorId;

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
}
