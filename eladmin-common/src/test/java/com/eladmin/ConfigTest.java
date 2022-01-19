package com.eladmin;

import com.eladmin.utils.DateUtil;
import org.junit.Test;
import com.eladmin.jsonconfig.Config;

public class ConfigTest {
    @Test
    public void test1() throws Exception{
        String host = Config.dataSources().getUrl();

        System.out.print(host);
    }
}
