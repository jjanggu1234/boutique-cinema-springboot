package com.cinema.config;

import com.cinema.formatter.LocalDateFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomServletConfig  implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {

    }
}
