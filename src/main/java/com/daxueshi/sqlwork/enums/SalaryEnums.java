package com.daxueshi.sqlwork.enums;

import lombok.Getter;

@Getter
public enum  SalaryEnums {
//    O(0,"1-1000",0),//其他状态情况下
//    A(1,"1000-6000",3000),
//    B(2,"6000-10000",8000),
//    C(3,"10000-15000",12500),
//    D(4,"15000-20000",17500),
//    E(5,"20000-25000",22500),
//    F(6,"25000-30000",27500),
//    G(7,"30000-35000",32500),
//    H(8,"35000-40000",37500)
    O(0,"1-1000",0),//其他状态情况下
    A(1,"1000-5000",3000),
    B(2,"5000-8000",7000),
    C(3,"8000-12000",9000),
    D(4,"12000-13000",12500),
    E(5,"13000-15000",14000),
    F(6,"15000-20000",17500),
    G(7,"20000-30000",22500),
    H(8,"30000-",37500)
    ;
    private Integer level;
    private String range;
    private Integer average;

    SalaryEnums(Integer level, String range, Integer average) {
        this.level = level;
        this.range = range;
        this.average = average;
    }
}
