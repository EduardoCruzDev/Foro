package com.eduardocruzdev.foro.topic;

import com.eduardocruzdev.foro.domain.model.Topic;
import com.eduardocruzdev.foro.domain.utiles.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;


@Slf4j
public class TopicPathArgumentResolver implements HandlerMethodArgumentResolver {

    private final TopicRepository topicRepository;

    public TopicPathArgumentResolver(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(TopicPath.class) != null &&
                parameter.getParameterType().equals(Topic.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        @SuppressWarnings("unchecked") Map<String, String> pathVariables = (Map<String, String>) webRequest.getAttribute(
                HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
        if (pathVariables == null) {
            throw new NotFoundException();
        }
        String idStr = pathVariables.get("idTopic");
        if (idStr == null) {
            throw new NotFoundException();
        }
        int id = Integer.parseInt(idStr);
        log.debug("Resolving topic with id {}", idStr);
        return topicRepository.findById((long) id).orElseThrow(NotFoundException::new);
    }
}
