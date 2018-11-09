package com.bzdepot.images.exception;

import com.bzdepot.common.message.JsonReturn;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

import org.slf4j.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MultipartException.class)
    @ResponseBody
    public Object handleError1(MultipartException e) {
        logger.error(e.getCause().getMessage());
        return JsonReturn.SetMsg(10010,e.getCause().getMessage(),"");
    }

    @ExceptionHandler(FileUploadException.class)
    @ResponseBody
    public Object handleError2(FileUploadException e) {
        logger.error(e.getCause().getMessage());
        return JsonReturn.SetMsg(10010,e.getCause().getMessage(),"");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleError3(Exception e) {
        logger.error(e.getCause().getMessage());
        return JsonReturn.SetMsg(10010,e.getCause().getMessage(),"");
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Object handleError4(RuntimeException e) {
        logger.error(e.getCause().getMessage());
        return JsonReturn.SetMsg(10010,e.getCause().getMessage(),"");
    }
}
