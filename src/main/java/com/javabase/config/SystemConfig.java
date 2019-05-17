package com.javabase.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @NAME: SystemConfig
 * @DESC: 系统配置项目.
 * @USER: 孤狼.
 **/
@Configuration
public class SystemConfig {
	
	@PostConstruct
	void init() {
		System.setProperty("es.set.netty.runtime.available.processors", "false");
	}
}
