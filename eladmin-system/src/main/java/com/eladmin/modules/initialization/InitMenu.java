package com.eladmin.modules.initialization;

import com.alibaba.fastjson.JSONArray;
import com.eladmin.exception.BadRequestException;
import com.eladmin.modules.system.domain.Menu;
import com.eladmin.modules.system.service.MenuService;
import com.eladmin.jsonconfig.Config;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class InitMenu {
    private static final Logger log = LoggerFactory.getLogger(InitManager.class);
    private final  MenuService menuService ;
    private static final String ENTITY_NAME = "menu";

    @PostConstruct
    public void InitialMenu() throws Exception {
        //MenuQueryCriteria criteria = new MenuQueryCriteria();
        //List<MenuDto> menuDtoList = menuService.queryAll(criteria, true);
        if(null == menuService){
            log.info("没有菜单，开始进行初始化");
            JSONArray[] menu = Config.menu().getMenu();
            for(int i=0;i<menu.length;i++)
            {
                Menu resources = new Menu();
                for(int j=0;j<menu[i].size();j++){
                    //resources.setId(1);
                    log.info("初始化"+menu[i].get(j).toString()+"成功");
                }


                if (resources.getId() != null) {
                    throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
                }
                menuService.create(resources);
            }
        }


    }
}
