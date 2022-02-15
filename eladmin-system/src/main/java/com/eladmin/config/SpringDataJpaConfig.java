package com.eladmin.config;

import com.eladmin.jsonconfig.Config;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

/**
 * 注意：spring-data-jpa2.x版本需要spring版本为5.x
 * 否则会报Initialization of bean failed; nested exception is java.lang.AbstractMethodError错误
 * 参考：https://stackoverflow.com/questions/47558017/error-starting-a-spring-application-initialization-of-bean-failed-nested-excep
 * 搭配方案：spring4+spring-data-jpa1.x或spring5+spring-data-jpa2.x
 */
@Configuration
// 借助spring data实现自动化的jpa repository，只需编写接口无需编写实现类
// 相当于xml配置的<jpa:repositories base-package="com.example.repository" />
// repositoryImplementationPostfix默认就是Impl
// entityManagerFactoryRef默认就是entityManagerFactory
// transactionManagerRef默认就是transactionManager
@EnableJpaRepositories(basePackages = {"com.eladmin"},
        repositoryImplementationPostfix = "Impl",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
@EnableTransactionManagement    // 启用事务管理器
public class SpringDataJpaConfig {

    // 配置jpa厂商适配器（参见spring实战p320）
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() throws Exception {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        // 设置数据库类型（可使用org.springframework.orm.jpa.vendor包下的Database枚举类）
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        // 设置打印sql语句
        jpaVendorAdapter.setShowSql(Config.dataSources().getShowSql());
        // 设置不生成ddl语句
        jpaVendorAdapter.setGenerateDdl(Config.dataSources().getDdl());
        // 设置hibernate方言
        jpaVendorAdapter.setDatabasePlatform(Config.dataSources().getDialect());
        return jpaVendorAdapter;
    }

    // 配置实体管理器工厂
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        // 注入数据源
        emfb.setDataSource(dataSource);
        // 注入jpa厂商适配器
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        HashMap<String, Object> props = new HashMap<>(2);
        //Hibernate隐式命名策略
        props.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        props.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
        //批量插入和更新
        props.put("hibernate.jdbc.batch_size", "500");
        props.put("hibernate.jdbc.batch_versioned_data", "true");
        props.put("hibernate.order_inserts", "true");
        props.put("hibernate.order_updates", "true");

        emfb.setJpaPropertyMap(props);

        // 设置扫描基本包
        emfb.setPackagesToScan("com.eladmin");
        return emfb;
    }

    // 配置jpa事务管理器
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        // 配置实体管理器工厂
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }


}