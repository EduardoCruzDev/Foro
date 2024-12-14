package com.eduardocruzdev.foro.configuration;

import com.eduardocruzdev.foro.topic.TopicPathArgumentResolver;
import com.eduardocruzdev.foro.topic.TopicRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {


    private final TopicRepository topicRepository;

    public WebConfiguration(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/avatars/**")
                .addResourceLocations(
                        "file:src/main/resources/uploads/avatars/",
                        "file:src/main/resources/static/img/default-avatars/");
        String userHomePath = System.getProperty("user.home");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///" + userHomePath + "/forum/images/sections/");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new TopicPathArgumentResolver(topicRepository));
    }
}
