package com.daxueshi.sqlwork.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import org.jetbrains.annotations.NotNull;

@Data
@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    private String userId;

    @NotNull
    private String password;

    private String email;

    private String phone;

    private Integer permission_id;

    private String status;


}
