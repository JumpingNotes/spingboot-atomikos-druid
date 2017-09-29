package com.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @desc:
 * @author: 鱼唇的人类
 * @date: 2017/9/28 14:48
 */
@ConfigurationProperties(prefix = "spring.datasource.m2")
public class M2Properties extends CustomDataSource {
}
