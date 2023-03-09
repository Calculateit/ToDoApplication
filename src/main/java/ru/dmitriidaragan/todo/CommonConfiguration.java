package ru.dmitriidaragan.todo;

import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.dmitriidaragan.todo.resource.TaskResource;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("ru.dmitriidaragan.todo")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("ru.dmitriidaragan.todo.repository")
public class CommonConfiguration {

    @Value("${db.driver}")
    private String databaseDriver;
    @Value("${db.password}")
    private String databasePassword;
    @Value("${db.url}")
    private String databaseUrl;
    @Value("${db.username}")
    private String databaseUsername;
    @Value("${db.hibernate.dialect}")
    private String databaseHibernateDialect;
    @Value("${db.hibernate.show_sql}")
    private String databaseHibernateShowSQL;
    @Value("${db.entitymanager.packages.to.scan}")
    private String databaseEntityManagerPackagesToScan;
    @Value("${db.hibernate.hbm2ddl.auto}")
    private String databaseHibernateHdm2ddlAuto;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(databaseDriver);
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUsername);
        dataSource.setPassword(databasePassword);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(databaseEntityManagerPackagesToScan);

        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());

        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("db.hibernate.dialect", databaseHibernateDialect);
        properties.put("db.hibernate.show_sql", databaseHibernateShowSQL);
        properties.put("db.hibernate.hbm2ddl.auto", databaseHibernateHdm2ddlAuto);

        return properties;
    }

    @Bean
    public ResourceConfig getResourceConfig() {
        ResourceConfig config = new ResourceConfig();
        config.register(TaskResource.class);
        return config;
    }

}
