package com.daxueshi.sqlwork.domain;

import java.io.Serializable;

/**
 * @author onion
 * @date 2019-04-08 -20:49
 */
public class Student implements Serializable {
    private String userId;
    private Integer universityId;
    private Integer majorId;
    private Integer score;

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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
