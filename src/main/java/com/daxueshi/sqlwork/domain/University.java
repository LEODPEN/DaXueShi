package com.daxueshi.sqlwork.domain;

import java.io.Serializable;

/**
 * @author onion
 * @date 2019-04-08 -20:15
 */

public class University implements Serializable {


    private static final long serialVersionUID = 7716883622038586704L;
    private Integer universityId;
    private String universityName;
    private String city;
    //private List<Major> majorList;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

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

    /*public List<Major> getMajorList() {
        return majorList;
    }

    public void setMajorList(List<Major> majorList) {
        this.majorList = majorList;
    }
    */

    @Override
    public String toString() {
        return "University{" +
                "universityId=" + universityId +
                ", universityName='" + universityName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}