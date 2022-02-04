package com.eladmin.jsonconfig;

import com.alibaba.fastjson.JSONArray;
import com.eladmin.annotation.FieldDescribe;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitData {

    public static final JSONArray [] DEFAULT_MENUDATA= new JSONArray[18];
    public static final String [] DEFAULT_MENUCOLUMN= new String[20];
    public static final JSONArray [] DEFAULT_DICTDATA= new JSONArray[20];
    public static final String [] DEFAULT_DICTCOLUMN= new String[3];
    public static final JSONArray [] DEFAULT_DICTDETAILDATA= new JSONArray[5];
    public static final String [] DEFAULT_DICTDETAILCOLUMN= new String[20];

    public static InitData defaultInstance() {
        InitData o = new InitData();
        return o;
    }

    public InitData() {
        this.menuColumn = DEFAULT_MENUCOLUMN;
        this.menuData = DEFAULT_MENUDATA;
        this.dictColumn = DEFAULT_DICTCOLUMN;
        this.dictData = DEFAULT_DICTDATA;
        this.dictDetailData = DEFAULT_DICTDETAILDATA;
        this.dictDetailColumn = DEFAULT_DICTDETAILCOLUMN;
    }
    @FieldDescribe("菜单的列")
    private  String[] menuColumn;

    @FieldDescribe("初始菜单数据")
    private  JSONArray[] menuData;

    @FieldDescribe("字典的列")
    private  String[] dictColumn;

    @FieldDescribe("初始字典数据")
    private  JSONArray[] dictData;

    @FieldDescribe("字典详情的列")
    private  String[] dictDetailColumn;

    @FieldDescribe("初始字典详情数据")
    private  JSONArray[] dictDetailData;




}
