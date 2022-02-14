package com.eladmin.modules.system.domain;

import com.eladmin.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户角色
 * **/
@Entity
@Getter
@Setter
@Table(name="sys_users_jobs",indexes = {@Index(columnList = "job_id"),@Index(columnList = "user_id")})
public class UsersJobs extends BaseEntity implements Serializable{


    @Column(name = "user_id")
    @ApiModelProperty(value = "用户ID")
    private String userId;


    @Column(name = "job_id")
    @ApiModelProperty(value = "岗位ID")
    private String jobId;
}
