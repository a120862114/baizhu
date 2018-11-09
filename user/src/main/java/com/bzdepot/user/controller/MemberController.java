package com.bzdepot.user.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.user.model.Member;
import com.bzdepot.user.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping(value = "/update")
    public Object updateMember(@Valid @ModelAttribute Member member, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        System.out.println(member.toString());
        int Ok = memberService.updateUserInfo(member);
        if(Ok > 0){
            return JsonReturn.SetMsg(0,"更新用户信息成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新用户信息失败!","");
    }
}
