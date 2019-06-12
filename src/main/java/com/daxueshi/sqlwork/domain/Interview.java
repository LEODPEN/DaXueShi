package com.daxueshi.sqlwork.domain;

import lombok.Data;

/**
 * @author onion
 * @date 2019-06-12 -16:16
 */
@Data
public class Interview {
    private Integer id;
    private String company;
    private Double rate;
    private String nickname;
    private String feeling;
}
