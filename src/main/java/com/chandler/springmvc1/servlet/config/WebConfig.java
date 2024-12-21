package com.chandler.springmvc1.servlet.config;

import com.chandler.springmvc1.servlet.filter.LogFilter;
import com.chandler.springmvc1.servlet.filter.LogInterceptor;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static jakarta.servlet.DispatcherType.ERROR;
import static jakarta.servlet.DispatcherType.REQUEST;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/error", "/*.ico", "/error-page/**");
    }

//    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setDispatcherTypes(REQUEST, ERROR); //TODO: 해당 case에 한해서 필터 호출
        return filterRegistrationBean;
    }

}
