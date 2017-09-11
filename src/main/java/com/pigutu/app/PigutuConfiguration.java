package com.pigutu.app;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Mail: chk@terminus.io
 * Created by IceMimosa
 * Date: 2017/9/10
 */
@Configuration
@EnableAutoConfiguration
@EnableWebMvc
@ComponentScan({"com.pigutu.app"})
public class PigutuConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");

            container.addErrorPages(error401Page, error404Page, error500Page);
        });
    }

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
