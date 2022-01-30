package com.eladmin.modules.initialization;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eladmin.exception.BadRequestException;
import com.eladmin.modules.system.domain.Menu;
import com.eladmin.modules.system.service.MenuService;
import com.eladmin.jsonconfig.Config;
import com.eladmin.modules.system.service.dto.MenuDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class InitMenu {
    private static final Logger log = LoggerFactory.getLogger(InitManager.class);
    private final  MenuService menuService ;
    private static final String ENTITY_NAME = "menu";

    @PostConstruct
    public void InitialMenu() throws Exception {
        //获取一级菜单
        List<MenuDto> menus =  menuService.getMenus(0L);
        //如果没有一级菜单就开始进行初始化
        if(menus.size() == 0){
            log.info("没有菜单，开始进行初始化");
            JSONArray[] menu = Config.menu().getMenu();
            String[] columns = Config.menu().getColumns();
            for(int i=0;i<menu.length;i++)
            {
                Map<String,Object> map = new HashMap<>();
                for(int j=0;j<menu[i].size();j++){
                    if(menu[i].get(j)!=""){
                        map.put(columns[j],menu[i].get(j).toString());
                    }
                }
                Menu resources =  JSON.toJavaObject(new JSONObject(map), Menu.class);
                System.out.print(resources.toString());
                if (resources.getId() != null) {
                    throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
                }
                menuService.create(resources);
            }
        }


    }
}
