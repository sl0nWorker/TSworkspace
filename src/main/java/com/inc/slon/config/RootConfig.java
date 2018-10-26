package com.inc.slon.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.inc.slon")
public class RootConfig {
    @Bean
    public Logger logger(){
        return Logger.getLogger("application");
    }
}
