package com.jzli.conf;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

import static org.apache.coyote.http11.Constants.a;

/**
 * 获取第二个数据库的连接信息，在application.yml中配置，并指定特定的前缀
 */
@Configuration
public class SessionFactoryConfig implements EnvironmentAware {
    private static Logger logger = LoggerFactory.getLogger(SessionFactoryConfig.class);
    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.mybatis.");
    }

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) {
        try {
            SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
            sqlSessionFactory.setDataSource(dataSource);
            //获取mybatis-config.xml路径
            String config = propertyResolver.getProperty("config");
            sqlSessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(config));
            return sqlSessionFactory.getObject();
        } catch (Exception e) {
            logger.error("Could not confiure mybatis session factory", e);
            return null;
        }
    }

//	@Bean
//	public PlatformTransactionManager annotationDrivenTransactionManager() {
//		logger.info("数据库事务开启~~~~~~~~~~~~~~~~~~~~~~~~~~");
//		return new DataSourceTransactionManager(dataSource);
//	}
}
