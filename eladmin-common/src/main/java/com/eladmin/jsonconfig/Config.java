package com.eladmin.jsonconfig;

import com.alibaba.fastjson.JSON;
import com.eladmin.tools.ConfigTools;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import com.alibaba.fastjson.JSONObject;
import javax.naming.InitialContext;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Config {

    private static Config INSTANCE;

    public Config() {
    }

    public static final String  PATH_CONFIG_DATASOURCES = "config/DataSources.json";
    public static final String PATH_CONFIG_SERVER = "config/Server.json";
    public static final String PATH_CONFIG_MENU = "config/Menu.json";
    public static final String PATH_VERSION = "version.txt";
    public static final String PATH_CONFIG_TOKEN = "config/token.json";
    public static final String PATH_CONFIG_PUBLICKEY = "config/public.key";
    public static final String PATH_CONFIG_PRIVATEKEY = "config/private.key";
    public static final String PATH_CONFIG_MQ = "config/mq.json";
    public static final String PATH_CONFIG_CACHE = "config/cache.json";
    public static final String PATH_CONFIG_EMAIL = "config/email.json";
    public static final String PATH_CONFIG_PERSON = "config/person.json";
    public static final String PATH_CONFIG_WEB = "config/web.json";
    public static final String DIR_CONFIG = "config";
    public static final String RESOURCE_JDBC_PREFIX = "jdbc/";

    private static final String DEFAULT_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWcVZIS57VeOUzi8c01WKvwJK9uRe6hrGTUYmF6J/pI6/UvCbdBWCoErbzsBZOElOH8Sqal3vsNMVLjPYClfoDyYDaUlakP3ldfnXJzAFJVVubF53KadG+fwnh9ZMvxdh7VXVqRL3IQBDwGgzX4rmSK+qkUJjc3OkrNJPB7LLD8QIDAQAB";
    private static final String DEFAULT_PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJZxVkhLntV45TOLxzTVYq/Akr25F7qGsZNRiYXon+kjr9S8Jt0FYKgStvOwFk4SU4fxKpqXe+w0xUuM9gKV+gPJgNpSVqQ/eV1+dcnMAUlVW5sXncpp0b5/CeH1ky/F2HtVdWpEvchAEPAaDNfiuZIr6qRQmNzc6Ss0k8HsssPxAgMBAAECgYAWtRy05NUgm5Lc6Og0jVDL/mEnydxPBy2ectwzHh2k7wIHNi8XhUxFki2TMqzrM9Dv3/LySpMl4AE3mhs34LNPy6F+MwyF5X7j+2Y6MflJyeb9HNyT++viysQneoOEiOk3ghxF2/GPjpiEF79wSp+1YKTxRAyq7ypV3t35fGOOEQJBANLDPWl8b5c3lrcz/dTamMjHbVamEyX43yzQOphzkhYsz4pruATzTxU+z8/zPdEqHcWWV39CP3xu3EYNcAhxJW8CQQC2u7PF5Xb1xYRCsmIPssFxil64vvdUadSxl7GLAgjQ9ULyYWB24KObCEzLnPcT8Pf2Q0YQOixxa/78FuzmgbyfAkA7ZFFV/H7lugB6t+f7p24OhkRFep9CwBMD6dnZRBgSr6X8d8ZvfrD2Z7DgBMeSva+OEoOtlNmXExZ3lynO9zN5AkAVczEmIMp3DSl6XtAuAZC9kD2QODJ2QToLYsAfjiyUwsWKCC43piTuVOoW2KUUPSwOR1VZIEsJQWEcHGDQqhgHAkAeZ7a6dVRZFdBwKA0ADjYCufAW2cIYiVDQBJpgB+kiLQflusNOCBK0FT3lg8BdUSy2D253Ih6l3lbaM/4M7DFQ";


    public static Path command_java_path() throws Exception {
        Path dir = Paths.get(System.getProperty("java.home"));
        return SystemUtils.IS_OS_WINDOWS ? dir.resolve("bin/java.exe") : dir.resolve("bin/java");
    }



    public static synchronized void flush() {
        INSTANCE = null;
    }

    private static synchronized Config instance() {
        if (null == INSTANCE) {
            INSTANCE = new Config();
        }
        return INSTANCE;
    }


    private DataSources dataSources;

    public static synchronized DataSources dataSources() throws Exception {
        if (null == instance().dataSources) {
            DataSources obj = ConfigTools.readConfigObject(PATH_CONFIG_DATASOURCES, DataSources.class);
            if (null == obj) {
                obj = DataSources.defaultInstance();
            }
            instance().dataSources = obj;
        }
        return instance().dataSources;
    }

    private String version;

    public static synchronized String version() throws Exception {
        if (null == instance().version) {
            String text = ConfigTools.readString(PATH_VERSION);
            instance().version = text;
        }
        return instance().version;
    }



    private String base;

    public static synchronized String base() throws Exception {
        if (null == instance().base) {
            instance().base = ConfigTools.getBasePath();
        }
        return instance().base;
    }


    private Token token;

    public static synchronized Token token() throws Exception {
        if (null == instance().token) {
            Token o = ConfigTools.readConfigObject(PATH_CONFIG_TOKEN, Token.class);
            if (null == o) {
                o = Token.defaultInstance();
            }
            instance().token = o;
        }
        return instance().token;
    }


    private Menu menu;

    public static synchronized Menu menu() throws Exception {
        if (null == instance().token) {
            Menu o = ConfigTools.readConfigObject(PATH_CONFIG_MENU, Menu.class);
            if (null == o) {
                o = Menu.defaultInstance();
            }
            instance().menu = o;
        }
        return instance().menu;
    }


    private String publicKey;

    public static synchronized String publicKey() throws Exception {
        if (null == instance().publicKey) {
            File file = new File(Config.base(), PATH_CONFIG_PUBLICKEY);
            if (file.exists() && file.isFile()) {
                instance().publicKey = FileUtils.readFileToString(file, "utf-8");
            } else {
                instance().publicKey = DEFAULT_PUBLIC_KEY;
            }
        }
        return instance().publicKey;
    }

    private String privateKey;

    public static synchronized String privateKey() throws Exception {
        if (null == instance().privateKey) {
            File file = new File(Config.base(), PATH_CONFIG_PRIVATEKEY);
            if (file.exists() && file.isFile()) {
                instance().privateKey = FileUtils.readFileToString(file, "utf-8");
            } else {
                instance().privateKey = DEFAULT_PRIVATE_KEY;
            }
        }
        return instance().privateKey;
    }




    private Server server;

    public static synchronized Server server() throws Exception {
        if (null == instance().server) {
            Server obj = ConfigTools.readConfigObject(PATH_CONFIG_SERVER, Server.class);
            if (null == obj) {
                obj = Server.defaultInstance();
            }
            instance().server = obj;
        }
        return instance().server;
    }





    private InitialContext initialContext;

    private static synchronized InitialContext initialContext() throws Exception {
        if (null == instance().initialContext) {
            instance().initialContext = new InitialContext();
        }
        return instance().initialContext;
    }







    public JSONObject web;

    public static synchronized JSONObject web() throws Exception {
        if (null == instance().web) {
            JSONObject obj = ConfigTools.readConfigObject(PATH_CONFIG_WEB, JSONObject.class);
            if (null == obj) {
                obj = new JSONObject();
            }
            instance().web = obj;
        }
        return instance().web;
    }



    public Map<String, JSONObject> customConfig = new HashMap<>();

    public static synchronized JSONObject customConfig(String configName) throws Exception {
        if (StringUtils.isBlank(configName)) {
            return null;
        } else {
            if (instance().customConfig.get(configName) == null) {
                JSONObject obj = ConfigTools.readConfigObject(DIR_CONFIG + "/" + configName + ".json", JSONObject.class);
                if (obj != null) {
                    instance().customConfig.put(configName, obj);
                }
            }
            return instance().customConfig.get(configName);
        }
    }


    public static Object resource(String name) throws Exception {
        return initialContext().lookup(name);
    }

    public static Object resource_jdbc(String name) throws Exception {
        return initialContext().lookup(RESOURCE_JDBC_PREFIX + name);
    }



}
