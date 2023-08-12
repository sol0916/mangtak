package com.delivery.springbootproject.config;

import com.delivery.springbootproject.util.UserAuthHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //인터셉터로 사용할 클래스 bean 등록
    @Bean
    public UserAuthHandler userAuthHandler() {
        return new UserAuthHandler();
    }

    //스프링 설정에 인터셉터 추가
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(userAuthHandler())
                .addPathPatterns("/notice/**")
                .addPathPatterns("/prodAdmin/**")
                .addPathPatterns("/prodDeliver/**")
                .addPathPatterns("/mypage/**")
                .addPathPatterns("/addr/**")
                .excludePathPatterns("/login")  ;

    }

}
