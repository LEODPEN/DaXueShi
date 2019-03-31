package com.daxueshi.sqlwork.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Major {

    @Id
    @GeneratedValue
    private Integer majorId;
}
