package com.eladmin.modules.system.domain;

import com.eladmin.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * 用户角色
 * **/
@Entity
@Getter
@Setter
@Table(name="sys_users_roles",indexes = {@Index(columnList = "role_id"),@Index(columnList = "user_id")})
public class UsersRoles  extends BaseEntity implements Serializable{
    @Transient
    private User user;

    @Column(name = "user_id")
    @ApiModelProperty(value = "用户ID")
    private String userId;


    @Transient
    private Role role;

    @Column(name = "role_id")
    @ApiModelProperty(value = "角色ID")
    private String roleId;
}
