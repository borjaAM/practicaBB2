package com.bbsw.bitboxer2.practica;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // your reactjs URL
                .allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE")
                .allowedHeaders("Content-Type"); // Adjust headers you need to allow
    }

}
