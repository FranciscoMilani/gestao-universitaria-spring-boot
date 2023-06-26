package br.ucs.ffmilani.GestaoUni.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
