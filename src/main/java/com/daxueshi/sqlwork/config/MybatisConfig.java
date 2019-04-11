package com.daxueshi.sqlwork.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Configuration;

/**
 * @author onion
 * @date 2019-04-10 -16:05
 */
@Configuration
public class MybatisConfig {
    public ConfigurationCustomizer customize(){
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
