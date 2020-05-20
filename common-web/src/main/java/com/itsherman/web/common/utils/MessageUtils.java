package com.itsherman.web.common.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;

public class MessageUtils implements MessageSourceAware {

    private static MessageSource messageSource;

    public static String getMessage(String msg, boolean includeVar, String... variables) {
        if (includeVar && variables != null && variables.length > 0) {
            for (int i = 0; i < variables.length; i++) {
                variables[i] = messageSource.getMessage(variables[i].replaceAll(" ", ".").toLowerCase(), null, LocaleContextHolder.getLocale());
            }
        }
        return messageSource.getMessage(msg.replaceAll(" ", ".").toLowerCase(), variables, LocaleContextHolder.getLocale());

    }


    @Override
    public void setMessageSource(MessageSource messageSource) {
        MessageUtils.messageSource = messageSource;
    }
}
