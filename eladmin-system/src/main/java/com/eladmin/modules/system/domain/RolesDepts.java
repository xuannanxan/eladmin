package com.eladmin.modules.system.domain;

import com.eladmin.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 数据权限
 * **/
@Entity
@Getter
@Setter
@Table(name="sys_roles_depts",indexes = {@Index(columnList = "role_id"),@Index(columnList = "dept_id")})
public class RolesDepts extends BaseEntity implements Serializable {


    @Column(name = "dept_id")
    @ApiModelProperty(value = "部门ID")
    private String deptId;


    @Column(name = "role_id")
    @ApiModelProperty(value = "角色ID")
    private String roleId;
}
