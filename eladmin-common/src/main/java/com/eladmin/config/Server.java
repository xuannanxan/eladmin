package com.eladmin.config;


import com.eladmin.annotation.FieldDescribe;
import com.eladmin.json.JsonPropertyObject;
import org.apache.commons.lang3.BooleanUtils;

public class Server extends JsonPropertyObject {

    public static final String DEFAULT_PROJECTNAME = "MC";
    public static final String DEFAULT_PROJECTDESCRIPTION = "基础平台";
    public static final String DEFAULT_HOST = "127.0.0.1";
    public static final Integer DEFAULT_PORT = 10010;

    public static final Boolean DEFAULT_DOCENABLE = true;
    public static final String DEFAULT_DOCNAME = "接口文档";
    public static final String DEFAULT_DOCDESCRIPTION = "API接口文档";


    public static Server defaultInstance() {
        return new Server();
    }

    public Server() {
        this.projectName = DEFAULT_PROJECTNAME;
        this.projectDescription = DEFAULT_PROJECTDESCRIPTION;
        this.host = DEFAULT_HOST;
        this.port = DEFAULT_PORT;
        this.docEnable = DEFAULT_DOCENABLE;
        this.docName = DEFAULT_DOCNAME;
        this.docDescription = DEFAULT_DOCDESCRIPTION;


    }

    @FieldDescribe("项目名称")
    private String projectName;

    @FieldDescribe("项目描述")
    private String projectDescription;

    @FieldDescribe("后端服务地址")
    private String host;

    @FieldDescribe("后端服务端口")
    private Integer port;

    @FieldDescribe("是否启用接口文档，默认是")
    private Boolean docEnable;


    @FieldDescribe("文档名称")
    private String docName;

    @FieldDescribe("文档描述")
    private String docDescription;


    @FieldDescribe("管理员邮箱")
    private String adminEmail;


    public String getProjectName() {
        return projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public String getHost() { return this.host; }

    public Integer getPort() {return this.port;}

    public Boolean getDocEnable() {
        return BooleanUtils.isTrue(this.docEnable);
    }

    public String getDocName() {
        return docName;
    }

    public String getDocDescription() {return docDescription;}

}
