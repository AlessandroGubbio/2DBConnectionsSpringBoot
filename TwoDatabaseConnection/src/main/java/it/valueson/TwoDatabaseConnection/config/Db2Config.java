package it.valueson.TwoDatabaseConnection.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "teacherEntityManagerFactory",
        basePackages = {"it.valueson.TwoDatabaseConnection.db2.repository"},
        transactionManagerRef = "teacherTransactionManager"
)
public class Db2Config {

    @Autowired
    Environment environment;

    @Primary
    @Bean(name = "teacherDataSource")
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(environment.getProperty("teacher.datasource.username"));
        dataSource.setPassword(environment.getProperty("teacher.datasource.password"));
        dataSource.setUrl(environment.getProperty("teacher.datasource.url"));
        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("teacher.datasource.driver-class-name")));
        return dataSource;
    }

    @Primary
    @Bean(name = "teacherEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(adapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        factoryBean.setJpaPropertyMap(properties);
        factoryBean.setPackagesToScan("it.valueson.TwoDatabaseConnection.db2.entities");
        return factoryBean;
    }

    @Primary
    @Bean(name = "teacherTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("teacherEntityManagerFactory")EntityManagerFactory
                                                         entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }

}
