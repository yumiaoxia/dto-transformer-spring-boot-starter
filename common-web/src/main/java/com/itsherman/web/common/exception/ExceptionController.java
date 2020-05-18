package com.itsherman.web.common.exception;

import com.itsherman.web.common.enums.CommonResponseEnum;
import com.itsherman.web.common.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p> </p>
 *
 * @author 俞淼霞
 * @since 2019-09-04
 */
@ControllerAdvice
public class ExceptionController {

    private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @Resource
    private MessageSource messageSource;

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ApiResponse handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        CommonResponseEnum responseEnum = CommonResponseEnum.SYSTEM_ERROR;
        String message = responseEnum.getMessage();
        String code = responseEnum.getCode();
        return ApiResponse.createError(code, message);
    }

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public ApiResponse handleServiceException(ServiceException ex) {
        log.error(ex.getMessage(), ex);
        if (ex.getCause() != null) {
            log.error(ex.getCause().getMessage(), ex.getCause());
        }
        Object[] args = ex.getArgs();
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                String argStr = args[i].toString();
                args[i] = messageSource.getMessage(argStr, null, LocaleContextHolder.getLocale());
            }
        }
        String msg = messageSource.getMessage(ex.getMessage().replaceAll(" ", ".").toLowerCase(), args, LocaleContextHolder.getLocale());
        return ApiResponse.createError(ex.getCode(), msg);
    }
}
