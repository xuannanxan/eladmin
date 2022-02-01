package com.eladmin.modules.system.domain;

import com.eladmin.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 操作权限
 * **/
@Entity
@Getter
@Setter
@Table(name="sys_roles_menus",indexes = {@Index(columnList = "role_id"),@Index(columnList = "menu_id")})
public class RolesMenus extends BaseEntity implements Serializable {
    @Transient
    private Menu dept;

    @Column(name = "menu_id")
    @ApiModelProperty(value = "菜单ID")
    private String menuId;

    @Transient
    private Role role;

    @Column(name = "role_id")
    @ApiModelProperty(value = "角色ID")
    private String roleId;
}
