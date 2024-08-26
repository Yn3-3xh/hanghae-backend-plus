package org.example.frameworkstudy.handler.request;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CustomRequestMappingHandler extends RequestMappingHandlerMapping {

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo requestMappingInfo = super.getMappingForMethod(method, handlerType);
        if (requestMappingInfo == null) {
            return null;
        }

        List<String> superclassUrlPatterns = getStrings(handlerType);

        if (!superclassUrlPatterns.isEmpty()) {
            RequestMappingInfo superclassRequestMappingInfo = RequestMappingInfo.paths(
                    String.join("", superclassUrlPatterns))
                    .build();
            return superclassRequestMappingInfo.combine(requestMappingInfo);
        } else {
            return requestMappingInfo;
        }
    }

    private static List<String> getStrings(Class<?> handlerType) {
        List<String> superclassUrlPatterns = new ArrayList<>();
        boolean isInherited = false;
        for (Class<?> clazz = handlerType; clazz != Object.class; clazz = clazz.getSuperclass()) {
            if (clazz.isAnnotationPresent(RequestMapping.class)) {
                if (isInherited) {
                    superclassUrlPatterns.add(clazz.getAnnotation(RequestMapping.class).value()[0]);
                } else {
                    isInherited = true;
                }
            }
        }
        return superclassUrlPatterns;
    }
}
