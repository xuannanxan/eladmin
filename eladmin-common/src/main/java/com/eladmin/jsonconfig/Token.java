package com.eladmin.jsonconfig;

import com.eladmin.annotation.FieldDescribe;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class Token  {


    public static final String  DEFAULT_INITIALMANAGER = "admin";

    public static final String DEFAULT_NICKNAME = "系统管理员";

    public static final String DEFAULT_PASSWORD = "123456";
    public static final String DEFAULT_EMAIL = "admin@admin.com";
    public static final String DEFAULT_PHONE = "18088888888";
    public static final String  surfix = "eladmin";

    public static Token defaultInstance() {
        Token o = new Token();
        return o;
    }

    public Token() {
        this.key = "";
        this.nickName = DEFAULT_NICKNAME;
        this.password = DEFAULT_PASSWORD;
        this.manager = DEFAULT_INITIALMANAGER;
        this.email=DEFAULT_EMAIL;
        this.phone=DEFAULT_PHONE;
    }

    @FieldDescribe("初始管理员昵称")
    private String nickName;
    public String getNickName() {return this.nickName;}
    public void setNickName(String nickName) {this.nickName = nickName;}

    @FieldDescribe("初始管理员密码")
    private String password;
    public String getPassword() {return this.password;}
    public void setPassword(String password) {this.password = password;}

    @FieldDescribe("初始管理员名称,目前不可更改.")
    private String manager;
    public String getManager() {
        return StringUtils.isEmpty(this.manager) ? DEFAULT_INITIALMANAGER : this.manager;
    }
    public void setManager(String manager) {this.manager = manager;}

    @FieldDescribe("管理员邮箱")
    private String email;
    public String getEmail() {return this.email;}
    public void setEmail(String email) {this.email = email;}

    @FieldDescribe("管理员电话")
    private String phone;
    public String getPhone() {return this.phone;}
    public void setPhone(String phone) {this.phone = phone;}

    // 加密用的key,用于加密口令
    @FieldDescribe("加密用口令的密钥,修改后会导致用户口令验证失败.")
    private String key;
    // 前面的代码是 key+surfix 结果是nullo2platform
    public String getKey() {
        String val = Objects.toString(key, "") + surfix;
        return StringUtils.substring(val, 0, 8);
    }

    public void setKey(String key) {
        if (StringUtils.equals(key, StringUtils.substring(surfix, 0, 8))) {
            this.key = null;
        } else {
            this.key = key;
        }
    }

}
