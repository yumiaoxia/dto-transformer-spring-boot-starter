package com.itsherman.dtoassembler.task;

import com.itsherman.dtoassembler.annotations.ViewSelector;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class RequestDtoInterceptor implements HandlerInterceptor {

    private Class<?> viewClass;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            Method method = hm.getMethod();
            this.viewClass = Void.class;
            ViewSelector viewSelector = method.getAnnotation(ViewSelector.class);
            if (viewSelector != null) {
                this.viewClass = viewSelector.selectView();
            }
        }
        return true;
    }

    public Class<?> getViewClass() {
        return this.viewClass;
    }

}
