package com.pcbang.order.mvp.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.sql.SQLException;

@Slf4j
@Configuration
@ComponentScan(includeFilters = @Filter(OptionalComponent.class))
@Profile("local")
public class H2ServerConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource dataSource() throws SQLException {
        Server server = defaultRun();
        if(server.isRunning(true)){
            log.info("H2 Database Run Success");
        }
        log.info("H2 Server URL = {}", server.getURL());

        return new HikariDataSource();
    }

    private Server adviceRun(int port, String externalDbName, String dbname, FilePath db_store) throws SQLException {
        return Server.createTcpServer(
                "-tcp",
                "-tcpAllowOthers",
                "-ifNotExists",
                "-tcpPort", port+"", "-key", externalDbName, db_store.value2(dbname)).start();
    }

    private Server defaultRun() throws SQLException{
        return Server.createTcpServer(
                "-tcp",
                "-tcpAllowOthers",
                "-ifNotExists",
                "-tcpPort", 9092 +"").start();
    }

    enum FilePath {
        absolute("~/"),
        relative("./");
        String prefix;
        FilePath(String prefix){
            this.prefix = prefix;
        }
        public String value2(String dbname){
            return prefix + dbname;
        }
    }
}

@RequiredArgsConstructor
@Slf4j
@OptionalComponent
class H2DefaultDataInitalizer {
    @PostConstruct
    public void run(){
        log.info("H2DefaultInitalizer Run");
    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface OptionalComponent {
}