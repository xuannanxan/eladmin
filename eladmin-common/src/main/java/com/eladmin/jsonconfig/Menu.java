package com.eladmin.jsonconfig;

import com.alibaba.fastjson.JSONArray;
import com.eladmin.annotation.FieldDescribe;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

@Getter
@Setter
public class Menu {

    public static final JSONArray [] DEFAULT_MENU= new JSONArray[18];
    public static final String [] DEFAULT_COLUMNS= new String[18];

    public static Menu defaultInstance() {
        Menu o = new Menu();
        return o;
    }

    public Menu() {
        this.columns = DEFAULT_COLUMNS;
        this.menu = DEFAULT_MENU;
    }
    @FieldDescribe("菜单的列")
    private  String[] columns;


    @FieldDescribe("初始菜单数据")
    private  JSONArray[] menu;



}
