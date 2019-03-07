package com.imooc.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "girl")
@RefreshScope
@Data
public class GirlConfig {
    private String name;
    private Integer age;
}
