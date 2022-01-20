package com.eladmin.jsonconfig;


import com.eladmin.annotation.FieldDescribe;
import org.apache.commons.lang3.BooleanUtils;

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

    public String getDriverClassName() { return this.driverClassName; }
    public void  setDriverClassName(String driverClassName) {  this.driverClassName = driverClassName; }

    @FieldDescribe("数据库链接字符串")
    private String url;
    public String getUrl() { return this.url; }
    public void  setUrl(String url) {  this.url = url; }

    @FieldDescribe("用户名")
    private String username;
    public String getUsername() { return this.username; }
    public void  setUsername(String username) {  this.username = username; }

    @FieldDescribe("密码")
    private String password;
    public String getPassword() { return this.password; }
    public void  setPassword(String password) {  this.password = password; }

    @FieldDescribe("初始连接数")
    private Integer initSize;
    public Integer getInitSize() { return this.initSize; }
    public void  setInitSize(Integer initSize) {  this.initSize = initSize; }

    @FieldDescribe("最小连接数")
    private Integer minIdle;
    public Integer getMinIdle() { return this.minIdle; }
    public void  setMinIdle(Integer minIdle) {  this.minIdle = minIdle; }

    @FieldDescribe("最大连接数")
    private Integer maxAct;
    public Integer getMaxAct() { return this.maxAct; }
    public void  setMaxAct(Integer maxAct) {  this.maxAct = maxAct; }

    @FieldDescribe("获取连接超时时间")
    private Integer maxWait;
    public Integer getMaxWait() { return this.maxWait; }
    public void  setMaxWait(Integer maxWait) {  this.maxWait = maxWait; }


    @FieldDescribe("druid 地址")
    private String urlPattern;
    public String getUrlPattern() { return this.urlPattern; }
    public void  setUrlPattern(String urlPattern) {  this.urlPattern = urlPattern; }

    @FieldDescribe("druid 地址")
    private String allow;
    public String getAllow() { return this.allow; }
    public void  setAllow(String deny) {  this.allow = allow; }

    @FieldDescribe("druid 地址")
    private String deny;
    public String getDeny() { return this.deny; }
    public void  setDeny(String deny) {  this.deny = deny; }

    @FieldDescribe("druid 地址")
    private String druidUsername;
    public String getDruidUsername() { return this.druidUsername; }
    public void  setDruidUsername(String druidUsername) {  this.druidUsername = druidUsername; }

    @FieldDescribe("druid 地址")
    private String druidPassword;
    public String getDruidPassword() { return this.druidPassword; }
    public void  setDruidPassword(String druidPassword) {  this.druidPassword = druidPassword; }

    @FieldDescribe("配置监控统计")
    private Boolean resetEnable;
    public Boolean getResetEnable() {
        return BooleanUtils.isTrue(this.resetEnable);
    }
    public void setResetEnable(Boolean resetEnable) {this.resetEnable = resetEnable;}

    @FieldDescribe("超时时间(以秒数为单位)")
    private Integer slowSqlMillis;
    public Integer getSlowSqlMillis() { return this.slowSqlMillis; }
    public void  setSlowSqlMillis(Integer slowSqlMillis) {  this.slowSqlMillis = slowSqlMillis; }
}
