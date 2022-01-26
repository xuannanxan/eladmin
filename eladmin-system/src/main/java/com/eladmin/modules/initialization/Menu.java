package com.eladmin.modules.initialization;

import com.eladmin.modules.system.service.MenuService;
import com.eladmin.jsonconfig.Config;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class Menu {
    private static final Logger log = LoggerFactory.getLogger(Manager.class);
    private final  MenuService menuService ;

    @PostConstruct
    public void InitialMenu() throws Exception {
        Object[] menu = Config.menu().getMenu();
        for(int i=0;i<menu.length;i++)
        {
            for(int j=0;j<menu[i].length;j++){
                System.out.println(menu[i][j]);
            }

        }
        //如果用户不存在
//        if (menuService.queryAll('',) == null) {
//            log.info(Config.token().getNickName()+"不存在，开始进行初始化...");
//
//            menuService.create();
//            log.info("初始化"+Config.token().getNickName()+"成功");
//        }
    }
}
