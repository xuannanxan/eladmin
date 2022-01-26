package com.eladmin.tools;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.io.FileUtils;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ConfigTools {
    public static String getBasePath() throws IOException, URISyntaxException {
        return getBaseDirectory().toAbsolutePath().toString();
    }

    /**
     * 从Main.class所在的目录开始递归向上,找到version.txt所在目录,就是程序根目录.
     *
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    private static Path getBaseDirectory() throws IOException, URISyntaxException {
        Path path = Paths.get(
                new URI("file://" + ConfigTools.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
        while (Files.exists(path)) {
            Path versionFile = path.resolve("version.txt");
            if (Files.exists(versionFile) && Files.isRegularFile(versionFile)) {
                return path.toAbsolutePath();
            }
            path = path.getParent();
        }
        throw new IOException("没有找到根目录.");
    }
    //    读取配置文件
    public static <T> T readConfigObject(String path, Class<T> cls) throws Exception {
        String base = ConfigTools.getBasePath();
        File file = new File(base, path);
        if ((!file.exists()) || file.isDirectory()) {
            return null;
        }
        String json = FileUtils.readFileToString(file, DefaultCharset.charset);
        //字符串转Object
        JSONObject  jsonObject = JSONObject.parseObject(json);
        if ((null != jsonObject) ) {
            Map<String,Object> map = jsonObject;
            //移除###注释行对象
            removeComment(map);
            //map转Object
            jsonObject = new JSONObject(map);
        }
        return JSON.toJavaObject(jsonObject, cls);
    }
    /**
     * 完成子节点的转换
     * 1、如果是JSONObject，表示还没有到底层，继续递归；
     * 2、如果是JSONArray，表示是一个数组对象，我们直接拿出来就行；
     * 3、如果是String类型，表示是根节点，直接输出
     * **/
    private static void transformChildrenNode(JSONObject jb){
        for(String key : jb.keySet()) {
            if(jb.get(key) instanceof JSONObject){
                JSONObject childJb = JSONObject.parseObject(String.valueOf(jb.get(key)));
                transformChildrenNode(childJb);
            }else if(jb.get(key) instanceof JSONArray){
                JSONArray childArr = (JSONArray) jb.get(key);
                System.out.println(key+":"+childArr);
            }else{
                System.out.println(key+":"+jb.get(key));
            }

        }
    }

    private static void removeComment(Map<String, Object> map) {
        List<Map.Entry<String, Object>> entries = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (StringUtils.startsWith(Objects.toString(entry.getKey()), "#")) {
                entries.add(entry);
                continue;
            } else {
                if (entry.getValue() instanceof Map) {
                    removeComment((Map<String, Object>) entry.getValue());
                }
            }
        }
        for (Map.Entry<String, Object> entry : entries) {
            map.remove(entry.getKey());
        }
    }
    public static <T> T readConfigObject(String path, String otherPath, Class<T> cls) throws Exception {
        String base = ConfigTools.getBasePath();
        File file = new File(base, path);
        if (file.exists() && file.isFile()) {
            return readConfigObject(path, cls);
        }
        file = new File(base, otherPath);
        if (file.exists() && file.isFile()) {
            return readConfigObject(otherPath, cls);
        }
        throw new Exception("未找到文件:" + path + ", otherPath:" + otherPath + ".");
    }
    public static String readString(String path) throws IOException, URISyntaxException {
        String base = ConfigTools.getBasePath();
        File file = new File(base, path);
        if ((!file.exists()) || file.isDirectory()) {
            return null;
        }
        return FileUtils.readFileToString(file, DefaultCharset.charset);
    }

}
