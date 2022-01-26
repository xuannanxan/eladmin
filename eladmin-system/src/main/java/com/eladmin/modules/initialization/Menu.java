package com.eladmin.modules.initialization;

import com.alibaba.fastjson.JSONArray;
import com.eladmin.modules.system.service.MenuService;
import com.eladmin.jsonconfig.Config;
import com.eladmin.modules.system.service.dto.MenuDto;
import com.eladmin.modules.system.service.dto.MenuQueryCriteria;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class Menu {
    private static final Logger log = LoggerFactory.getLogger(Manager.class);
    private final  MenuService menuService ;

    @PostConstruct
    public void InitialMenu() throws Exception {
        MenuQueryCriteria criteria = new MenuQueryCriteria();
        List<MenuDto> menuDtoList = menuService.queryAll(criteria, true);

        JSONArray[] menu = Config.menu().getMenu();


        for(int i=0;i<menu.length;i++)
        {
            for(int j=0;j<menu[i].size();j++){
                log.info("初始化"+menu[i].get(j).toString()+"成功");
            }
        }

    }
}
