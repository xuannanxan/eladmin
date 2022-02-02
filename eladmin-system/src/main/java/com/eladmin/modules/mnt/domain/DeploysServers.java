package com.eladmin.modules.mnt.domain;

import com.eladmin.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 部署的服务
 * **/
@Entity
@Getter
@Setter
@Table(name="mnt_deploys_servers",indexes = {@Index(columnList = "deploy_id"),@Index(columnList = "server_id")})
public class DeploysServers extends BaseEntity implements Serializable {
    @Transient
    private Deploy deploy;

    @Column(name = "deploy_id")
    @ApiModelProperty(value = "部署ID")
    private String deployId;


    @Transient
    private ServerDeploy serverDeploy;

    @Column(name = "server_id")
    @ApiModelProperty(value = "岗位ID")
    private String serverId;
}
