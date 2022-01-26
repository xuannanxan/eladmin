package com.eladmin.jsonconfig;

import com.alibaba.fastjson.JSONArray;
import com.eladmin.annotation.FieldDescribe;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class Menu {

    public static final JSONArray [] DEFAULT_MENU= new JSONArray[18];
    public static Menu defaultInstance() {
        Menu o = new Menu();
        return o;
    }

    public Menu() {
        this.menu = DEFAULT_MENU;
    }

    @FieldDescribe("初始菜单数据")
    private  JSONArray[] menu;
    public JSONArray[] getMenu() {  return this.menu;}
    public void setMenu(JSONArray[] menu) { this.menu = menu;}


}
