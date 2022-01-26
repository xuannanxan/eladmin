package com.eladmin;

import com.eladmin.jsonconfig.Config;
import org.junit.Test;

import java.util.Arrays;

public class MenuDataTest {
    @Test
    public void contextLoads() throws Exception {
        Object[] menu = Config.menu().getMenu();
        //System.out.println(Arrays.toString(menu));
        for(int i=0;i<menu.length;i++)
        {

            System.out.println(getType(menu[i]));
        }

    }
    public static String getType(Object test) {
        return test.getClass().getName().toString();

    }
    public static void main(String[] args) {

    }
}
