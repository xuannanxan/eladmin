package com.eladmin.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import com.eladmin.jsonconfig.Config;
import java.sql.SQLException;

/**
 * 纯java config的方式去配置druid
 * javaBean方式注册数据库连接池
 */

@Configuration
public class DruidConfig {
    @Bean
    public DruidDataSource getDataSourceConfig() throws Exception {
        DruidDataSource ds = new DruidDataSource();
        // 基本属性
        ds.setDriverClassName(Config.dataSources().getDriverClassName());
        ds.setUrl(Config.dataSources().getUrl());
        ds.setUsername(Config.dataSources().getUsername());
        ds.setPassword(Config.dataSources().getPassword());
        // 配置初始化大小、最小、最大
        ds.setInitialSize(Config.dataSources().getInitSize());
        ds.setMinIdle(Config.dataSources().getMinIdle());
        ds.setMaxActive(Config.dataSources().getMaxAct());
        // 配置获取连接等待超时的时间
        ds.setMaxWait(60000);
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        ds.setTimeBetweenEvictionRunsMillis(60000);
        // 配置一个连接在池中最小生存的时间，单位是毫秒
        ds.setMinEvictableIdleTimeMillis(300000);
        ds.setValidationQuery("SELECT 'X'");
        ds.setTestWhileIdle(true);
        ds.setTestOnBorrow(false);
        ds.setTestOnReturn(false);

        // 打开PSCache，并且指定每个连接上PSCache的大小
        ds.setPoolPreparedStatements(false);
        ds.setMaxPoolPreparedStatementPerConnectionSize(20);
        // 配置监控统计拦截的filters
        ds.setFilters("stat,wall");

//        #      db-type: com.alibaba.druid.pool.DruidDataSource
//#      driverClassName: net.sf.log4jdbc.sql.jdbcapi.DriverSpy
//#      url: jdbc:log4jdbc:mysql://${DB_HOST:localhost}:${DB_PORT:3306}/${DB_NAME:eladmin}?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useSSL=false
//#      username: ${DB_USER:root}
//#      password: ${DB_PWD:123456}
//#      # 初始连接数
//#      initial-size: 5
//#      # 最小连接数
//#      min-idle: 15
//#      # 最大连接数
//#      max-active: 30
//#      # 超时时间(以秒数为单位)
//#      remove-abandoned-timeout: 180
//#      # 获取连接超时时间
//#      max-wait: 3000
//#      # 连接有效性检测时间
//#      time-between-eviction-runs-millis: 60000
//#      # 连接在池中最小生存的时间
//#      min-evictable-idle-time-millis: 300000
//#      # 连接在池中最大生存的时间
//#      max-evictable-idle-time-millis: 900000
//#      # 指明连接是否被空闲连接回收器(如果有)进行检验.如果检测失败,则连接将被从池中去除
//#      test-while-idle: true
//#      # 指明是否在从池中取出连接前进行检验,如果检验失败, 则从池中去除连接并尝试取出另一个
//#      test-on-borrow: true
//#      # 是否在归还到池中前进行检验
//#      test-on-return: false
//#      # 检测连接是否有效
//#      validation-query: select 1
//#      # 配置监控统计
//#      webStatFilter:
//#        enabled: true
//#      stat-view-servlet:
//#        enabled: true
//#        url-pattern: /druid/*
//#        reset-enable: false
//#      filter:
//#        stat:
//#          enabled: true
//#          # 记录慢SQL
//#          log-slow-sql: true
//#          slow-sql-millis: 1000
//#          merge-sql: true
//#        wall:
//#          config:
//#            multi-statement-allow: true

        return ds;

    }

    /*
     * JDBC模板
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DruidDataSource druidDataSource) throws SQLException {
        return new JdbcTemplate(druidDataSource);
    }

}