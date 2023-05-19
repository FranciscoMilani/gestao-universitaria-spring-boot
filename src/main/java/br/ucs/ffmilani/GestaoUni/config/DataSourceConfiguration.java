package br.ucs.ffmilani.GestaoUni.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

    @Configuration
        public class DataSourceConfiguration {
            @Bean(name = "datasource1")
            @Primary
            @ConfigurationProperties(prefix="postgres.datasource")
            public DataSource primaryDataSource(){
                return DataSourceBuilder.create().build();
            }

            @Bean(name = "datasource2")
            @ConfigurationProperties(prefix="mysql.datasource")
            public DataSource secondaryDataSource(){
                return DataSourceBuilder.create().build();
            }

//            @Bean
//            public JdbcTemplate postgresJdbcTemplate(DataSource postgresDataSource){
//                return new JdbcTemplate(postgresDataSource);
//            }
//
//            @Bean
//            public JdbcTemplate mysqlJdbcTemplate(DataSource mysqlDataSource){
//                return new JdbcTemplate(mysqlDataSource);
//            }
    }
