package com.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * @desc:
 * @author: 鱼唇的人类
 * @date: 2017/9/28 14:48
 */
@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "com.boot.domain.m1", entityManagerFactoryRef = "m1EntityManager")
@EnableConfigurationProperties(M1Properties.class)
public class M1Config {
    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;

    @Autowired
    private M1Properties m1Properties;

    @Primary
    @Bean(name = "m1DataSource", initMethod = "init", destroyMethod = "close")
    public DataSource customerDataSource() throws SQLException {
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(CustomDataSource.toDruidXADataSource(m1Properties));
        xaDataSource.setUniqueResourceName("m1");
        xaDataSource.setBorrowConnectionTimeout(60);
        xaDataSource.setMaxPoolSize(20);
        return xaDataSource;

    }

    @Primary
    @Bean(name = "m1EntityManager")
    @DependsOn("transactionManager")
    public LocalContainerEntityManagerFactoryBean customerEntityManager() throws Throwable {
        HashMap<String, Object> properties = new HashMap();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("javax.persistence.transactionType", "JTA");
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setJtaDataSource(customerDataSource());
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setPackagesToScan("com.boot.domain.entity");
        entityManager.setPersistenceUnitName("m1PersistenceUnit");
        entityManager.setJpaPropertyMap(properties);
        return entityManager;
    }
}
