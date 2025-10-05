package com.nmscinemas.ticket_booking_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    @Value("${DB_PATH:/data/nms-movies-ticketing.db}")
    private String dbPath;

    @Bean
    public DataSource dataSource(){

        SQLiteDataSource dataSource = new SQLiteDataSource();
        try {
            Path target = Paths.get(dbPath);
            Files.createDirectories(target.getParent());
            dataSource.setUrl("jdbc:sqlite:" + target.toString());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
