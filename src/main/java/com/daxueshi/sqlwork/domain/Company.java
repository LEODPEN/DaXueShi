package com.daxueshi.sqlwork.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Company {

    @Id
    private String companyId;
}
