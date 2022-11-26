package com.zmh.redis.config;

import com.zmh.redis.util.LowUp.LowUpChar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfig {

    @Bean
    public LowUpChar lowUpChar(){
        return new LowUpChar();
    }


}
