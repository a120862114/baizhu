package com.bzdepot.client.controller;

import com.bzdepot.client.model.ClientUser;
import com.bzdepot.client.service.UsersService;
import com.bzdepot.common.message.JsonReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UsersService usersService;

    @PostMapping(value = "/add/info")
    public Object AddUserInfo(@Valid @ModelAttribute ClientUser clientUser, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        System.out.println(clientUser.toString());
        int Ok = usersService.addClientUser(clientUser);
        if(Ok > 0){
            return JsonReturn.SetMsg(0,"创建用户子数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"创建用户子数据失败!","");
    }

    @PostMapping(value = "/login/clien")
    public Object getUserInfo(HttpServletRequest request){
        if(request.getParameter("id") == null){
            return JsonReturn.SetMsg(10010,"用户编号不能为空!","");
        }
        Long UserId = null;
        try {
            UserId = Long.parseLong(request.getParameter("id"));
        }catch (NumberFormatException e){
            return JsonReturn.SetMsg(10010,"用户编码转码失败!","");
        }
        System.out.print(UserId);
        ClientUser clientUser= usersService.getClienUser(UserId);
        if(clientUser!=null){
            return JsonReturn.SetMsg(0,"查询子用户成功!",clientUser);
        }else{
            return JsonReturn.SetMsg(10011,"查询的用户不存在!","");
        }

    }

    /**
     * 更新用户详细信息API接口
     * @param clientUser
     * @param result
     * @return
     */
    @PostMapping(value = "/update/info")
    public Object updateInfoApi(@Valid @ModelAttribute ClientUser clientUser,BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(usersService.updateClientUserFunc(clientUser) > 0){
            return JsonReturn.SetMsg(0,"更新用户详细信息成功!","");
        }
        return JsonReturn.SetMsg(10010,"更新用户详细信息失败!","");
    }
}
