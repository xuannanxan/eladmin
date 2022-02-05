package com.eladmin.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanMap;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 对象的工具类
 * */
public class ObjectTools {
    //map转java对象
    public static <T> T jsonToObject(String json, Class<T> cls) throws Exception {
        //字符串转Object
        JSONObject  jsonObject = JSONObject.parseObject(json);
        if (null == jsonObject ) {
            return  null;
        }
        return JSON.toJavaObject(jsonObject, cls);
    }
    /**
     * java对象转map
     */


    @SneakyThrows
    public static Map<String, Object> objectToMap(Object obj)  {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo
                .getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter != null ? getter.invoke(obj) : null;
            map.put(key, value);
        }
        return map;
    }

    /**
     *  map转java对象
     * @param bean 泛型类
     * @param <T>
     * @return T
     */
    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap =  new BeanMap(bean);
        beanMap.putAll(map);
        return bean;
    }

}
