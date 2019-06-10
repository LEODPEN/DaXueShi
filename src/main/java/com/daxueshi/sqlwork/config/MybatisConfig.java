package com.daxueshi.sqlwork.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author onion
 * @date 2019-04-10 -16:05
 */
@Configuration
public class MybatisConfig {
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();

        //将RowBounds第一个参数offset当作PageNum使用
        p.setProperty("offsetAsPageNum","true");
        //使用RowBounds分页会进行count查询
        p.setProperty("rowBoundsWithCount","true");
        p.setProperty("reasonable","true");
        p.setProperty("dialect","mysql");
        pageHelper.setProperties(p);
        return pageHelper;
    }

}
