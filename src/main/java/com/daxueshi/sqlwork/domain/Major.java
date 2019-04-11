package com.daxueshi.sqlwork.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @author onion
 * @date 2019-04-08 -20:19
 */
@Component
public class Major implements Serializable {
    private Integer majorId;
    private String majorName;
    private List<University> universities;

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public List<University> getUniversities() {
        return universities;
    }

    public void setUniversities(List<University> universities) {
        this.universities = universities;
    }
}
