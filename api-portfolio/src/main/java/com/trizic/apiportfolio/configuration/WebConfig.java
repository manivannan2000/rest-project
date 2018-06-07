package com.trizic.apiportfolio.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.trizic.apiportfolio.converter.ModelReqToModelConverter;
import com.trizic.apiportfolio.converter.ModelToModelResConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer{
 
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ModelReqToModelConverter());
        registry.addConverter(new ModelToModelResConverter());
    }
}