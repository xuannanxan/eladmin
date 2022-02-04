package com.eladmin.modules.initialization;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eladmin.exception.BadRequestException;
import com.eladmin.modules.system.domain.Menu;
import com.eladmin.modules.system.service.MenuService;
import com.eladmin.jsonconfig.Config;
import com.eladmin.modules.system.service.dto.MenuDto;
import com.eladmin.modules.system.service.dto.MenuQueryCriteria;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化菜单
 * **/

@RequiredArgsConstructor
@Component
public class InitMenu {
    private static final Logger log = LoggerFactory.getLogger(InitManager.class);
    private final  MenuService menuService ;
    private static final String ENTITY_NAME = "menu";

    @PostConstruct
    public void SetInitialMenu() throws Exception {
        JSONArray[] menu = Config.initData().getMenuData();
        InitialMenu(menu,"0");
    }
    private void InitialMenu(JSONArray[] menu ,String pid ) throws Exception {
        String[] columns = Config.initData().getMenuColumn();

        for (JSONArray item : menu) {
            Map<String,Object> map = new HashMap<>();
            for (int j = 0; j < item.size(); j++) {
                if ((!columns[j].equals("id") )||( !columns[j].equals("pid"))) {
                    map.put(columns[j], item.get(j).toString());
                }
            }
            //根据菜单名称和地址进行查询，不存在就进行新增
            MenuQueryCriteria criteria  = new MenuQueryCriteria();
            criteria.setBlurry(map.get("title").toString());
            List<MenuDto> result = menuService.queryAll(criteria,true);
            if(result.size()==0){
                log.info("Menu:"+map.get("title").toString()+"不存在，开始进行初始化...");
                map.put("pid",pid);
                Menu resources =  JSON.toJavaObject(new JSONObject(map), Menu.class);
                menuService.create(resources);
                result = menuService.queryAll(criteria,true);
            }

            //子菜单或按钮
            if (map.get("children")!= null){
                JSONArray jsonArray  = JSON.parseArray(map.get("children").toString());
                if(jsonArray.size()>0){
                    JSONArray[] childArray = new  JSONArray[jsonArray.size()];
                    for(int i=0;i<jsonArray.size();i++){
                        childArray[i]=JSON.parseArray(jsonArray.get(i).toString());
                    }
                    InitialMenu(childArray,result.get(0).getId());
                }

            }

        }
//
//
//
    }
}
