package com.newbee.cloud.excep;

import com.newbee.cloud.resp.RerurnCodeEnum;
import com.newbee.cloud.resp.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 全局异常类
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> globalException(Exception e){
        log.info("####################进入全局异常类{}###################",e.getMessage(),e);
        return ResultData.fail(RerurnCodeEnum.RC500.getCode(),e.getMessage());

    }
}
