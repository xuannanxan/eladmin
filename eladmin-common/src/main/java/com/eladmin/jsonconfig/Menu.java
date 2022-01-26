package com.eladmin.jsonconfig;

import com.eladmin.annotation.FieldDescribe;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;

public class Menu {


    public static Menu defaultInstance() {
        Menu o = new Menu();
        return o;
    }

    public Menu() {
        this.menu = new Object[0];
    }

    @FieldDescribe("初始菜单数据")
    private  Object[] menu;
    public Object[] getMenu() {return this.menu;}
    public void setMenu(Object[] menu) {this.menu = menu;}


}
