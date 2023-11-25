package org.ebook.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class GlobalCorsConfiguration implements WebMvcConfigurer {

    @Override
    public  void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedMethods("POST","PUT", "GET", "PATCH", "DELETE")
                .allowedOrigins("http://localhost:5501")
                .allowCredentials(true);

    }




}