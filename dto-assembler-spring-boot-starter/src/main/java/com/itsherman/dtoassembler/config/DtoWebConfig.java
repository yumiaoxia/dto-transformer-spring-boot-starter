package com.itsherman.dtoassembler.config;

import com.itsherman.dtoassembler.task.RequestDtoInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DtoWebConfig implements WebMvcConfigurer {

    @Bean
    public RequestDtoInterceptor requestDtoInterceptor() {
        return new RequestDtoInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestDtoInterceptor());
    }
}
