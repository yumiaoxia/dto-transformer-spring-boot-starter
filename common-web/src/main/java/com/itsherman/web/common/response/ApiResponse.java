package com.itsherman.web.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itsherman.web.common.enums.CommonResponseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p> </p>
 *
 * @author 俞淼霞
 * @since 2019-09-03
 */
@ApiModel
public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = -7416528189137018359L;

    @ApiModelProperty("请求是否成功")
    @JsonProperty(defaultValue = "true")
    private Boolean success;

    @ApiModelProperty("响应结果消息")
    @JsonProperty
    private String message;

    @ApiModelProperty("返回数据主体，载荷")
    @JsonProperty
    private T data;

    @ApiModelProperty("响应结果编码")
    @JsonProperty
    private String code;

    public static <T> ApiResponse<T> createSuccess() {
        return createSuccess(null);
    }

    public static <T> ApiResponse<T> createSuccess(T t) {
        return createSuccess(CommonResponseEnum.OK.getCode(), CommonResponseEnum.OK.getMessage(), t);
    }

    public static <T> ApiResponse<T> createSuccess(String code, String message, T t) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        initCodeAndMessage(apiResponse, code, message);
        apiResponse.setData(t);
        return apiResponse;
    }

    public static <T> ApiResponse<T> createError() {
        return createError(CommonResponseEnum.SYSTEM_ERROR.getCode(), CommonResponseEnum.SYSTEM_ERROR.getMessage());
    }

    public static <T> ApiResponse<T> createError(String code, String message) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(false);
        initCodeAndMessage(apiResponse, code, message);
        return apiResponse;
    }

    protected static void initCodeAndMessage(ApiResponse response, String code, String message) {
        response.setCode(code);
        response.setMessage(message);
    }


    public Boolean getSuccess() {
        return success;
    }

    public ApiResponse<T> setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ApiResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ApiResponse<T> setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                ", success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", code='" + code + '\'' +
                '}';
    }
}
