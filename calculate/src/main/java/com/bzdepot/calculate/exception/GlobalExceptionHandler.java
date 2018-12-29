package com.bzdepot.calculate.exception;

import com.bzdepot.common.message.JsonReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleError3(Exception e) {
        logger.error(e.toString());
        return JsonReturn.SetMsg(10010,e.toString(),"");
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Object handleError4(RuntimeException e) {
        logger.error(e.toString());
        return JsonReturn.SetMsg(10010,e.toString(),"");
    }
}
