package com.pigutu.app.intercept;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration // 标注此文件为一个配置项，spring boot才会扫描到该配置。该注解类似于之前使用xml进行配置
@EnableWebMvc
@ComponentScan
public class InterceptorAdapter extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index/1");
        registry.addViewController("/m").setViewName("forward:/m/index/1");
 /*       registry.addViewController("/401").setViewName("forward:http://img.pigutu.com/error.html");
        registry.addViewController("/404").setViewName("forward:http://img.pigutu.com/error.html");
        registry.addViewController("/500").setViewName("forward:http://img.pigutu.com/error.html");*/
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}