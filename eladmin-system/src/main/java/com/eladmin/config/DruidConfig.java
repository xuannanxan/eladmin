package com.eladmin.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
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
        ds.setMaxWait(Config.dataSources().getMaxWait());
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        ds.setTimeBetweenEvictionRunsMillis(60000);
        // 配置一个连接在池中最小生存的时间，单位是毫秒
        ds.setMinEvictableIdleTimeMillis(300000);
        ds.setValidationQuery("select 1");
        ds.setTestWhileIdle(true);
        ds.setTestOnBorrow(false);
        ds.setTestOnReturn(false);

        // 打开PSCache，并且指定每个连接上PSCache的大小
        ds.setPoolPreparedStatements(false);
        ds.setMaxPoolPreparedStatementPerConnectionSize(20);
        // 配置监控统计拦截的filters
        ds.setFilters("stat,wall");

        return ds;

    }
    @Bean
    public ServletRegistrationBean staViewServlet() throws Exception {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());

        servletRegistrationBean.addUrlMappings(Config.dataSources().getUrlPattern());
        //白名单为空允许任何ip访问
        servletRegistrationBean.addInitParameter("allow",Config.dataSources().getAllow());
        //ip黑名单(存在共同时，deny优先于allow)：如果满足deny的即提示：Sorry you are not permitted...
        servletRegistrationBean.addInitParameter("deny",Config.dataSources().getDeny());
        //登录查看信息的账号密码
        servletRegistrationBean.addInitParameter("loginUsername",Config.dataSources().getDruidUsername());
        servletRegistrationBean.addInitParameter("loginPassword",Config.dataSources().getDruidPassword());
        //是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable",Config.dataSources().getResetEnable().toString());
        //记录慢SQL
        servletRegistrationBean.addInitParameter("logSlowSql", "true");
        servletRegistrationBean.addInitParameter("slowSqlMillis", Config.dataSources().getSlowSqlMillis().toString());
        servletRegistrationBean.addInitParameter("mergeSql", "true");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean statFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpn,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    /*
     * JDBC模板
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DruidDataSource druidDataSource) throws SQLException {
        return new JdbcTemplate(druidDataSource);
    }

}