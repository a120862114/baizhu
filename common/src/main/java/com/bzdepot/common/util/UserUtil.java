package com.bzdepot.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/*
*  获取当前用户信息
* */
public class UserUtil {

    public static Long getId(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            String UserIdStr = request.getHeaders("user_id").nextElement();
            Long UserId = null;
            if(UserIdStr != null){
                try {
                    UserId = Long.parseLong(UserIdStr);
                }catch (NumberFormatException e){
                    return null;
                }
            }
            return UserId;
        }catch (Exception e){
            return null;
        }

    }

    public static Long getLevelId(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            String UserIdStr = request.getHeaders("levelId").nextElement();
            Long UserId = null;
            if(UserIdStr != null){
                try {
                    UserId = Long.parseLong(UserIdStr);
                }catch (NumberFormatException e){
                    return null;
                }
            }
            return UserId;
        }catch (Exception e){
            return null;
        }

    }

    public static Long getSellerId(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            String UserIdStr = request.getHeaders("sellerId").nextElement();
            Long UserId = null;
            if(UserIdStr != null){
                try {
                    UserId = Long.parseLong(UserIdStr);
                }catch (NumberFormatException e){
                    return null;
                }
            }
            return UserId;
        }catch (Exception e){
            return null;
        }

    }
    public static String getUsername(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            return request.getHeaders("username").nextElement();
        }catch (Exception e){
            return null;
        }

    }

    public static String getNickname(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            return request.getHeaders("nickname").nextElement();
        }catch (Exception e){
            return null;
        }
    }

    public static String getReToken(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            return request.getHeaders("retoken").nextElement();
        }catch (Exception e){
            return null;
        }
    }
}
