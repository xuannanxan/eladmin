package com.eladmin.modules.initialization;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eladmin.jsonconfig.Config;
import com.eladmin.modules.system.domain.Dict;
import com.eladmin.modules.system.domain.DictDetail;
import com.eladmin.modules.system.service.DictDetailService;
import com.eladmin.modules.system.service.DictService;
import com.eladmin.modules.system.service.dto.DictDetailDto;
import com.eladmin.modules.system.service.dto.DictDto;

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
public class InitDict {
    private static final Logger log = LoggerFactory.getLogger(InitManager.class);
    private final DictService dictService ;
    private final DictDetailService dictDetailService ;
    private static final String ENTITY_NAME = "dict";

    @PostConstruct
    public void SetInitialDict() throws Exception {
        JSONArray[] dict = Config.initData().getDictData();
        String[] columns = Config.initData().getDictColumn();
        for (JSONArray item : dict) {
            Map<String,Object> map = new HashMap<>();
            for (int j = 0; j < item.size(); j++) {
                map.put(columns[j], item.get(j).toString());
            }
            List<DictDto> result = dictService.findByName(map.get("name").toString());
            if(result.size()==0){
                log.info("Dict:"+map.get("name").toString()+"不存在，开始进行初始化...");
                Dict resources =  JSON.toJavaObject(new JSONObject(map), Dict.class);
                dictService.create(resources);
            }
        }
        SetInitialDictDetail();
    }
    private void SetInitialDictDetail() throws Exception {
        String[] columns = Config.initData().getDictDetailColumn();
        JSONArray[] dictDetail = Config.initData().getDictDetailData();
        for (JSONArray item : dictDetail) {
            Map<String,Object> map = new HashMap<>();
            for (int j = 0; j < item.size(); j++) {
                map.put(columns[j], item.get(j).toString());
            }
            List<DictDto> result = dictService.findByName(map.get("dictName").toString());
            boolean isNew = true;
            if(result.size()>0){
                map.put("dict",result.get(0));
                if(result.get(0).getDictDetails().size()>0){
                    for(DictDetailDto dd:result.get(0).getDictDetails()){
                        if(dd.getLabel().equals(map.get("label"))){
                            isNew = false;
                        }
                    }
                }
            }
            if(isNew){
                log.info("Dict:"+map.get("dictName").toString()+"/DictDetail:"+map.get("label").toString()+"不存在，开始进行初始化...");
                DictDetail resources =  JSON.toJavaObject(new JSONObject(map), DictDetail.class);
                dictDetailService.create(resources);
            }
        }
    }
}
