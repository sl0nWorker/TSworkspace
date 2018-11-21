package com.inc.slon.config;

import com.inc.slon.model.TruckerStatus;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan("com.inc.slon")
public class RootConfig {
    @Bean
    public Logger logger(){
        return Logger.getLogger("application");
    }
}
