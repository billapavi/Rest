package com.rotbot.billa.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.rotbot.billa.stocks","com.rotbot.billa.beans"})
@EnableJpaRepositories(basePackages = {"com.rotbot.billa.repositories"})
@EnableTransactionManagement
public class MysqlConfig {

}
