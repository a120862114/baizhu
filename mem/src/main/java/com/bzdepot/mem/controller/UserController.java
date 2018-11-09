package com.bzdepot.mem.controller;

import com.alibaba.fastjson.JSONObject;
import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.common.util.JwtUtil;
import com.bzdepot.mem.model.Member;
import com.bzdepot.mem.service.MemberService;
import com.bzdepot.mem.service.RegisterService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.InvalidClaimException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/*
* 有家的地方没有工作，有工作的地方没有家。他乡容不了灵魂，故乡安置不了肉身。从此便有了漂泊，有了乡愁！
* */

@RestController
@RequestMapping("/auth/user")
public class UserController {
    @Autowired
    private MemberService userService;
    @Autowired
    private RegisterService registerService;
    @Value("${tokenkey}")
    private  String TokenKey;


    public static final int NUM = 4;

    /*
    *   注册方法API
    * */
    @PostMapping("/register")
    public Object register(HttpServletRequest request, @Valid @ModelAttribute Member User, BindingResult result){

        //表单验证错误解析回执
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }

        //验证用户名是否存在
        Member FindUserResult = userService.findUserByUsername(User.getUsername());
        if(FindUserResult != null){
            return JsonReturn.SetMsg(10011,"用户名已存在,请重新填写!","");
        }
        //验证邮箱是否存在
        Member EmailResult = userService.findUserByEmail(User.getEmail());
        if(EmailResult != null){
            return JsonReturn.SetMsg(10011,"邮箱已存在,请重新填写!","");
        }
        //验证手机号码是否存在
        Member MobileResult = userService.findUserByMobile(User.getMobile());
        if(MobileResult != null){
            return JsonReturn.SetMsg(10011,"手机号码已存在,请重新填写!","");
        }
        int validCode=(Integer) registerService.validRegister(String.valueOf(User.getMobile()),User.getCode());
        if(validCode>1){
            return JsonReturn.SetMsg(10011,"手机验证码错误,请重新填写!","");
        }

        //验证两次密码是否输入一致
        String RePassword = request.getParameter("repassword");
        if(RePassword == null){
            return JsonReturn.SetMsg(10010,"确认密码不能为空!","");
        }
        if(!RePassword.equals(User.getPassword())){
            return JsonReturn.SetMsg(10010,"两次密码输入不一致!","");
        }


        //开始添加用户
        Long UserId = null;
        if(request.getParameter("user_type") == null){
            UserId = userService.addUser(User);
        }else{
            Integer UserType = null;
            try {
                UserType = Integer.parseInt(request.getParameter("user_type"));
            }catch (NumberFormatException e){
                return JsonReturn.SetMsg(10010,"获取用户类型失败!","");
            }
            Long SellerID = null;
            if(UserType != null && UserType == 1){
                if(request.getParameter("sellerId") != null){
                    try {
                        SellerID = Long.parseLong(request.getParameter("sellerId"));
                    }catch (NumberFormatException e){
                        return JsonReturn.SetMsg(10010,"商家编号获取失败!","");
                    }
                    if(SellerID != null){
                        Byte UserTypes = 0;
                        UserId = userService.addUserAndClient(User,SellerID,UserTypes);
                    }
                }
            }else if(UserType != null && UserType == 2){
                Byte UserTypes = 1;
                UserId = userService.addUserAndClient(User,null,UserTypes);
            }else if(UserType != null && UserType == 3){
                if(request.getParameter("sellerId") != null){
                    try {
                        SellerID = Long.parseLong(request.getParameter("sellerId"));
                    }catch (NumberFormatException e){
                        return JsonReturn.SetMsg(10010,"商家编号获取失败!","");
                    }
                    if(SellerID != null){
                        Byte UserTypes = 2;
                        UserId = userService.addUserAndClient(User,SellerID,UserTypes);
                    }
                }
            }
        }

        if(UserId == null){
            return JsonReturn.SetMsg(10012,"用户注册失败,请稍后再试!","");
        }

        return JsonReturn.SetMsg(0,"用户注册成功!",UserId);
    }

    /**
     * 通过手机号获取用户或商家信息
     * @param mobile
     * @param types
     * @return
     */
    @GetMapping(value = "/get/mobile/{mobile}/{types}")
    public Object getUserInfoByMobile(@PathVariable("mobile") Long mobile,@PathVariable("types") int types){
        if(mobile == null){
            return JsonReturn.SetMsg(10010,"手机号码不能为空!","");
        }
        Member MobileResult = userService.findUserByMobile(mobile); //获取手机号码
        if(MobileResult == null){
            return JsonReturn.SetMsg(0,"手机号码不存在可以注册!","");
        }
        MobileResult.setPassword(null);
        if(types == 0){
            return JsonReturn.SetMsg(10011,"手机号码已经存在不可以注册!",MobileResult);
        }
        Object obj= userService.getClientUser(MobileResult.getId());
        Map<String,Object> UserInfos = JsonReturn.Parse(obj);
        Integer Status = (Integer) UserInfos.get("error_code");
        if(Status < 1){
            Map<String,Object> Data = new HashMap<String, Object>();
            Data.put("username",MobileResult.getUsername());
            Data.put("nickname",MobileResult.getNickname());
            Data.put("user_id",MobileResult.getId());
            JSONObject userRes = (JSONObject) UserInfos.get("data");
            Data.put("sellerId",userRes.get("sellerId"));
            Data.put("levelId",userRes.get("levelId"));
            Data.put("userType",userRes.get("userType"));
            return JsonReturn.SetMsg(0,"获取商家信息成功!",Data);
        }
        return JsonReturn.SetMsg(10011,"获取用户信息失败!","");
    }

    /**
     * 通过用户名获取用户信息
     * @param username
     * @return
     */
    @GetMapping(value = "/get/username/{username}")
    public Object getUserInfoByUsername(@PathVariable("username") String username){
        if(username == null){
            return JsonReturn.SetMsg(10010,"用户名不能为空!","");
        }
        //验证用户名是否存在
        Member FindUserResult = userService.findUserByUsername(username);
        if(FindUserResult != null){
            FindUserResult.setPassword(null);
            return JsonReturn.SetMsg(0,"获取用户信息成功!",FindUserResult);
        }
        return JsonReturn.SetMsg(10011,"获取用户信息失败!","");
    }

    @GetMapping(value = "/get/email/{email}")
    public Object getUserInfoByEmail(@PathVariable("email") String email){
        if(email == null){
            return JsonReturn.SetMsg(10010,"邮箱地址不能为空!","");
        }
        Member EmailResult = userService.findUserByEmail(email);
        if(EmailResult != null){
            EmailResult.setPassword(null);
            return JsonReturn.SetMsg(0,"获取用户信息成功!",EmailResult);
        }
        return JsonReturn.SetMsg(10011,"获取用户信息失败!","");
    }

    /**
     * 根据用户编号获取用户信息
     * @param userid
     * @return
     */
    @GetMapping(value = "/get/id/{userid}")
    public Object getUserById(@PathVariable("userid") Long userid){
        if(userid == null){
            return JsonReturn.SetMsg(10010,"用户编号不能为空!","");
        }
        Member results = userService.findUserById(userid);
        if(results != null){
            results.setPassword(null);
            return JsonReturn.SetMsg(0,"获取用户信息成功!",results);
        }
        return JsonReturn.SetMsg(10011,"获取用户信息失败或用户不存在!","");
    }
    /*
    *  完善用户信息资料的API
    * */
    @PostMapping(value = "/perfect")
    public  Object Perfect(HttpServletRequest request){
        return JsonReturn.SetMsg(0,"用户资料补充成功!","");
    }

    /*
    *   登陆的API方法
    *
    * */
    @PostMapping(value = "/login")
    public Object Login(HttpServletRequest request){
        String Type = request.getParameter("type"); //登陆账号的类型
        if(Type == null){
            return JsonReturn.SetMsg(10010,"请使用正确的方式登陆!","");
        }
        String TokenStr = null;
        Member UserResult = null;
        String Password = request.getParameter("password");
        if(Password == null){
            return JsonReturn.SetMsg(10010,"请填写密码！","");
        }
        //判定是那种账号登陆
        switch (Type) {
            case "email":
                String Email = request.getParameter("email");
                if(Email == null){
                    return JsonReturn.SetMsg(10010,"请填写用户名或邮箱或手机号码登陆!","");
                }
                UserResult = userService.loginForUserEmail(Email,Password);
                break;
            case "mobile":
                String Mobile = request.getParameter("mobile");
                if(Mobile == null){
                    return JsonReturn.SetMsg(10010,"请填写用户名或邮箱或手机号码登陆!","");
                }
                Long MobileLong = null;
                try {
                    MobileLong = Long.valueOf(Mobile);
                }catch (Exception e){
                    return JsonReturn.SetMsg(10010,"传递的手机号码类型错误!","");
                }
                if(MobileLong == null){
                    return JsonReturn.SetMsg(10010,"手机号码类型或格式错误!","");
                }
                UserResult = userService.loginForUserMobile(MobileLong,Password);

                break;
            case "username":
                String Username = request.getParameter("username");
                if(Username == null){
                    return JsonReturn.SetMsg(10010,"请填写用户名或邮箱或手机号码登陆!","");
                }
                UserResult = userService.loginForUserMemberUsername(Username,Password);
                break;
            default:
                return JsonReturn.SetMsg(10010,"参数异常！","");


        }

        if(UserResult == null){
            return JsonReturn.SetMsg(10011,"登陆失败，账号或密码错误！","");
        }

        Object obj= userService.getClientUser(UserResult.getId());
        System.out.println(obj);
        Map<String,Object> UserInfos = JsonReturn.Parse(obj);
        Integer Status = (Integer) UserInfos.get("error_code");

        //为登陆者办法Token令牌
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("username", UserResult.getUsername());
        claims.put("nickname", UserResult.getNickname());
        claims.put("user_id", UserResult.getId());
        if(Status < 1){
            JSONObject userRes = (JSONObject) UserInfos.get("data");
            claims.put("sellerId",userRes.get("sellerId"));
            claims.put("levelId",userRes.get("levelId"));
            claims.put("userType",userRes.get("userType"));
        }
        String TokenString = JwtUtil.createToken(this.TokenKey,claims);
        if(TokenString == null){
            return JsonReturn.SetMsg(10011,"为颁发用户Token证书失败!","");
        }
        //构造回发数据
        Map<String,Object> Data = new HashMap<String, Object>();
        Data.put("username",UserResult.getUsername());
        Data.put("nickname",UserResult.getNickname());
        Data.put("user_id",UserResult.getId());
        Data.put("token",TokenString);

        if(Status < 1){
            JSONObject userRes = (JSONObject) UserInfos.get("data");
            Data.put("sellerId",userRes.get("sellerId"));
            Data.put("levelId",userRes.get("levelId"));
            Data.put("headImgId",userRes.get("headImgId"));
            Data.put("userType",userRes.get("userType"));
        }

        return JsonReturn.SetMsg(0,"登陆成功!",Data);

    }

    //Token验证的APi方法
    @PostMapping(value="/check/token")
    public  Object CheckToken(HttpServletRequest request){
        String TokenStr = request.getParameter("token");
        if(TokenStr == null){
            return JsonReturn.SetMsg(10010,"Token令牌不能为空!","");
        }
        Map<String,Object> Data = null;
        try{
            Claims claims = JwtUtil.parseToken(TokenStr,this.TokenKey);
            String nickname = claims.get("nickname").toString();
            String username = claims.get("username").toString();
            String user_id = claims.get("user_id").toString();
            Data = new HashMap<String, Object>();
            Data.put("nickname",nickname);
            Data.put("username",username);
            Data.put("user_id",user_id);
        } catch(ExpiredJwtException e) {
            return JsonReturn.SetMsg(10011,"TOKEN已过期","");
        } catch (InvalidClaimException e) {
            return JsonReturn.SetMsg(10012,"TOKEN无效","");
        } catch (Exception e) {
            return JsonReturn.SetMsg(10013,"TOKEN错误","");
        }
        if(Data == null){
            return JsonReturn.SetMsg(10014,"Token解析出错,无任何参数信息!","");
        }
        return JsonReturn.SetMsg(0,"Token解析成功!",Data);
    }


    //修改账号密码
    @PostMapping(value = "/update/mobile")
    public Object updatemobile(HttpServletRequest request,BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }

        Long  id=Long.parseLong(request.getParameter("id"));
        String  password=request.getParameter("password");

        int i= userService.updateByMemberMobile(password,id);
        if(i>0){
            return JsonReturn.SetMsg(0,"修改成功","");
        }else {
            return JsonReturn.SetMsg(10013,"修改失败","");
        }

    }

}
