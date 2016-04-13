package com.mnorris.jersey.springaop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Jsr330ScopeMetadataResolver;

@EnableAspectJAutoProxy
@Configuration
@ComponentScan(basePackages = {
		"com.mnorris" }, excludeFilters = @ComponentScan.Filter(value = Configuration.class, type = FilterType.ANNOTATION), scopeResolver = Jsr330ScopeMetadataResolver.class)
public class SpringApplicationConfig {

}
