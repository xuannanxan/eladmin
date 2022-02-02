package com.eladmin.jsonconfig;


import com.eladmin.annotation.FieldDescribe;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.BooleanUtils;

@Getter
@Setter
public class DataSources {

    public static final String DEFAULT_DRIVERCLASSNAME = "net.sf.log4jdbc.sql.jdbcapi.DriverSpy";
    public static final String DEFAULT_URL = "jdbc:log4jdbc:mysql://localhost:3306/eladmin?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useSSL=false";
    public static final String DEFAULT_USERNAME = "root";
    public static final String DEFAULT_PASSWORD = "123456";
    public static final Integer DEFAULT_INITSIZE = 5;
    public static final Integer DEFAULT_MINIDLE = 15;
    public static final Integer DEFAULT_MAXACT = 30;
    public static final Integer DEFAULT_MAXWAIT = 3000;
    public static final String DEFAULT_URLPATTERN = "/druid/*";
    public static final String DEFAULT_ALLOW = "127.0.0.1";
    public static final String DEFAULT_DENY = "";
    public static final String DEFAULT_DRUIDUSERNAME = "admin";
    public static final String DEFAULT_DRUIDPASSWORD = "123456";
    public static final Boolean DEFAULT_RESETENABLE = false;
    public static final Integer DEFAULT_SLOWSQLMILLIS = 1000;


    public static DataSources defaultInstance() {
        return new DataSources();
    }

    public DataSources() {
        this.driverClassName = DEFAULT_DRIVERCLASSNAME;
        this.url = DEFAULT_URL;
        this.username = DEFAULT_USERNAME;
        this.password = DEFAULT_PASSWORD;
        this.initSize = DEFAULT_INITSIZE;
        this.minIdle = DEFAULT_MINIDLE;
        this.maxAct = DEFAULT_MAXACT;
        this.maxWait = DEFAULT_MAXWAIT;
        this.urlPattern = DEFAULT_URLPATTERN;
        this.allow = DEFAULT_ALLOW;
        this.deny = DEFAULT_DENY;
        this.druidUsername = DEFAULT_DRUIDUSERNAME;
        this.druidPassword = DEFAULT_DRUIDPASSWORD;
        this.resetEnable = DEFAULT_RESETENABLE;
        this.slowSqlMillis = DEFAULT_SLOWSQLMILLIS;

    }

    @FieldDescribe("驱动名称")
    private String driverClassName;


    @FieldDescribe("数据库链接字符串")
    private String url;

    @FieldDescribe("用户名")
    private String username;

    @FieldDescribe("密码")
    private String password;

    @FieldDescribe("初始连接数")
    private Integer initSize;


    @FieldDescribe("最小连接数")
    private Integer minIdle;

    @FieldDescribe("最大连接数")
    private Integer maxAct;


    @FieldDescribe("获取连接超时时间")
    private Integer maxWait;


    @FieldDescribe("druid 地址")
    private String urlPattern;

    @FieldDescribe("druid 地址")
    private String allow;

    @FieldDescribe("druid 地址")
    private String deny;

    @FieldDescribe("druid 地址")
    private String druidUsername;

    @FieldDescribe("druid 地址")
    private String druidPassword;

    @FieldDescribe("配置监控统计")
    private Boolean resetEnable;

    @FieldDescribe("超时时间(以秒数为单位)")
    private Integer slowSqlMillis;

}
