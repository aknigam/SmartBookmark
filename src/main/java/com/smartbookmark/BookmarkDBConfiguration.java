package com.smartbookmark;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by a.nigam on 30/01/17.
 */
@Configuration
public class BookmarkDBConfiguration {


    private static final String ENV_NAME = "dev";


    @Bean
    @Primary
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/learn-qa");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    SqlSessionFactory sqlSessionFactory(DataSource dataSource){

        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        org.apache.ibatis.mapping.Environment myBatisEnvironment =
                new org.apache.ibatis.mapping.Environment(ENV_NAME, transactionFactory, dataSource);
        org.apache.ibatis.session.Configuration mybatisConfiguration = new org.apache.ibatis.session.Configuration(myBatisEnvironment);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(mybatisConfiguration);
        mybatisConfiguration.addMappers("com.smartbookmark.repository.mybatis.mapper");
        return sessionFactory;
    }



}
