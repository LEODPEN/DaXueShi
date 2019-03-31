package com.daxueshi.sqlwork.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class University {


    @Id
    @GeneratedValue
    private Integer universityId;
}
