package br.ucs.ffmilani.GestaoUni.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

    @Configuration
        public class DataSourceConfiguration {
            @Bean(name = "datasource1")
            @ConditionalOnProperty(name = "database.type", havingValue = "postgres")
            @ConfigurationProperties(prefix="postgres.datasource")
            public DataSource primaryDataSource(){
                return DataSourceBuilder.create().build();
            }

            //@Primary
            @Bean(name = "datasource2")
            @ConditionalOnProperty(name = "database.type", havingValue = "mysql")
            @ConfigurationProperties(prefix="mysql.datasource")
            public DataSource secondaryDataSource(){
                return DataSourceBuilder.create().build();
            }

//            @Bean(name = "datasource3")
//            @ConditionalOnProperty(name = "database.type", havingValue = "h2")
//            @ConfigurationProperties(prefix="h2.datasource")
//            public DataSource tertiaryDataSource(){
//                return DataSourceBuilder.create().build();
//            }


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
