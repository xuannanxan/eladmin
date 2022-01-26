package com.eladmin;

import com.alibaba.fastjson.JSONArray;
import com.eladmin.jsonconfig.Config;
import com.eladmin.modules.system.service.MenuService;
import com.eladmin.modules.system.service.dto.MenuDto;
import com.eladmin.modules.system.service.dto.MenuQueryCriteria;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

public class MenuDataTest {
    @Autowired
    private  MenuService menuService ;


    @Test
    public void contextLoads() throws Exception {

        MenuQueryCriteria criteria = new MenuQueryCriteria();
        List<MenuDto> menuDtoList = menuService.queryAll(criteria, true);
        if(null == menuDtoList){
            System.out.println("Arrays.toString(menuDtoList.toArray())");
        }
        JSONArray[] menu = Config.menu().getMenu();

        for(int i=0;i<menu.length;i++)
        {
            for(int j=0;j<menu[i].size();j++){
                System.out.println(menu[i].get(j));
            }

        }

    }
    public static String getType(Object test) {
        return test.getClass().getName().toString();

    }
    public static void main(String[] args) {

    }
}
