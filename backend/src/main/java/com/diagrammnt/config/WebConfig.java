package com.diagrammnt.config;

import com.diagrammnt.security.SsoFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**").allowedOriginPatterns("*").allowedMethods("GET","POST","PUT","DELETE","OPTIONS").allowedHeaders("*").allowCredentials(true).maxAge(3600);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }
    @Bean
    public FilterRegistrationBean<SsoFilter> ssoFilterRegistration(SsoFilter ssoFilter) {
        FilterRegistrationBean<SsoFilter> reg = new FilterRegistrationBean<>();
        reg.setFilter(ssoFilter);
        reg.addUrlPatterns("/*");
        reg.setOrder(Integer.MIN_VALUE);
        reg.setName("ssoFilter");
        return reg;
    }
    @Bean
    public FilterRegistrationBean<SpaFallbackFilter> spaFallbackFilterRegistration(SpaFallbackFilter f) {
        FilterRegistrationBean<SpaFallbackFilter> reg = new FilterRegistrationBean<>();
        reg.setFilter(f);
        reg.addUrlPatterns("/*");
        reg.setOrder(Integer.MIN_VALUE + 1);
        reg.setName("spaFallbackFilter");
        return reg;
    }
}
