package com.example.demo.validatedandvalid.interceptor;


import com.example.demo.common.DemoResponse;
import com.example.demo.common.ResponseMessageEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * controller层全局异常处理，确保不返回异常及堆栈信息
 *
 * @Author AKzhaoning
 */
@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
//
//    @Value("${interfaceDebug}")
//    Boolean interfaceDebug;

    /**
     * 处理 Bean validator 返回的信息
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
        DemoResponse response = new DemoResponse();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，错误信息
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            response.setCode(ResponseMessageEnum.ERROR.getCode());
            response.setMessage(error.getDefaultMessage());
        }
        return response;
    }

    @ExceptionHandler(Exception.class)
    public DemoResponse exceptionHandler(Exception e) {
        DemoResponse response = new DemoResponse();
        response.setCode(ResponseMessageEnum.ERROR.getCode());
        response.setMessage(e.getMessage());
        //调试模式下返回错误原因，生产环境统一返回 固定错误原因
//        if (!interfaceDebug) {
//        apayResponse.setMessage(ResponseMessageEnum.ERROR.getMessage());
////        }
        e.printStackTrace();
        return response;
    }
}
