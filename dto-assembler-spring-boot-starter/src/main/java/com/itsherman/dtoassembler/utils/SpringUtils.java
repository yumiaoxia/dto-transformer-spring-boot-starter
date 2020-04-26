package com.itsherman.dtoassembler.utils;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;


@Lazy(false)
public final class SpringUtils implements ApplicationContextAware, DisposableBean, InitializingBean {
    private static ApplicationContext applicationContext;
    private static LocaleResolver staticLocaleResolver;
    @Autowired(
            required = false
    )
    private LocaleResolver localeResolver;

    public SpringUtils() {
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtils.applicationContext = applicationContext;
    }

    public static Object getBean(String name) {
        Assert.hasText(name);
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> type) {
        Assert.hasText(name);
        Assert.notNull(type);
        return applicationContext.getBean(name, type);
    }

    public static String getMessage(String code, Object... args) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return getMessage(code, request, args);
    }

    public static String getMessage(String code, HttpServletRequest request, Object... args) {
        Locale locale = null;
        if (request != null && staticLocaleResolver != null) {
            locale = staticLocaleResolver.resolveLocale(request);
        }

        if (locale == null) {
            locale = Locale.getDefault();
        }

        return applicationContext.getMessage(code, args, locale);
    }

    public static String getMessage(MessageSourceResolvable resolvable, Locale locale) {
        MessageSource messageSource = (MessageSource) getBean("messageSource", MessageSource.class);
        if (locale == null) {
            locale = Locale.getDefault();
        }

        return messageSource.getMessage(resolvable, locale);
    }

    public void afterPropertiesSet() throws Exception {
        staticLocaleResolver = this.localeResolver;
    }

    public void destroy() throws Exception {
        applicationContext = null;
    }
}
