package com.wangchenglong.myblog.config;

import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.checkerframework.checker.units.qual.C;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Wangchenglong
 * @Date: 2024/8/27 11:15
 * @Description: UserAgentAnalyzer
 */
@Configuration
public class UserAgentConfig {

    @Bean
    public UserAgentAnalyzer userAgentAnalyzer() {
        UserAgentAnalyzer uaa = UserAgentAnalyzer
                .newBuilder()
                .hideMatcherLoadStats()
                .withCache(10000)
                .build();
        return uaa;
    }
}