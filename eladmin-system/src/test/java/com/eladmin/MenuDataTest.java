package com.eladmin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.eladmin.exception.BadRequestException;
import com.eladmin.jsonconfig.Config;
import com.eladmin.modules.initialization.InitManager;
import com.eladmin.modules.system.domain.Menu;
import com.eladmin.modules.system.service.MenuService;
import com.eladmin.modules.system.service.dto.MenuDto;
import com.eladmin.modules.system.service.dto.MenuQueryCriteria;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.eladmin.tools.ObjectTools;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuDataTest {
    @Autowired
    private  MenuService menuService ;
    private static final Logger log = LoggerFactory.getLogger(InitManager.class);
    private static final String ENTITY_NAME = "menu";

    @Test
    public void contextLoads() throws Exception {

        if(null == menuService){
            log.info("没有菜单，开始进行初始化");
            JSONArray[] menu = Config.menu().getMenu();
            String[] columns = Config.menu().getColumns();
            for(int i=0;i<menu.length;i++)
            {
                Map<String,Object> map = new HashMap<>();

                for(int j=0;j<menu[i].size();j++){
                    map.put(columns[j],menu[i].get(j).toString());
                }
                String  json= JSON.toJSONString(map);
                System.out.print(json);

                Menu resources =  ObjectTools.jsonToObject(json,Menu.class);
                System.out.print(resources.toString());
                if (resources.getId() != null) {
                    throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
                }
                menuService.create(resources);
            }
        }

    }
    public static String getType(Object test) {
        return test.getClass().getName().toString();

    }
    public static void main(String[] args) {

    }
}
