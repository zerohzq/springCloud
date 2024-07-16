package com.newbee.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @Author zero
 * @Description :
 * @CreateTime 2024/7/16 , 23:48
 */
@Configuration
public class FeignConfig {
    @Bean
    public Retryer myReTry(){
        //不开启重试模式
      //  return Retryer.NEVER_RETRY;
        return new Retryer.Default(100,2,3);
    }

    @Bean
    Logger.Level FeignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
