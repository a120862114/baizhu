package com.bzdepot.client.controller;

import com.bzdepot.client.model.ClientUser;
import com.bzdepot.client.model.LevelModule;
import com.bzdepot.client.model.UserLevel;
import com.bzdepot.client.service.UserLevelService;
import com.bzdepot.client.service.UsersService;
import com.bzdepot.client.vo.*;
import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.common.util.UserUtil;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/level/")
public class UserLevelController {

    private final static Logger loger = LoggerFactory.getLogger(UserLevelController.class);

    @Autowired
    private UserLevelService userLevelService;

    @Autowired
    private UsersService usersService;
    /**
     * 添加用户等级接口
     * @param userLevel
     * @param result
     * @return
     */
    @PostMapping(value = "/add")
    public Object addUserLevelApi(@Valid @ModelAttribute UserLevel userLevel, BindingResult result) {
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                loger.error(result.getFieldError().getField()+"参数传递的类型错误!");
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }

        Long LevelId = userLevelService.addUserLevel(userLevel);
        if(LevelId != null){
            return JsonReturn.SetMsg(0,"创建用户等级数据成功!",LevelId);
        }
        return JsonReturn.SetMsg(10011,"创建用户等级数据失败!","");
    }

    @PostMapping(value = "/edit")
    public Object editUserLeveApi(@Valid @ModelAttribute UserLevel userLevel,BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                loger.error(result.getFieldError().getField()+"参数传递的类型错误!");
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(userLevel.getId() == null){
            return JsonReturn.SetMsg(10010,"等级编号不能为空!","");
        }
        int Status = userLevelService.editUserLevel(userLevel);
        if(Status > 0){
            return JsonReturn.SetMsg(0,"更新用户等级数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新用户等级数据失败!","");
    }

    @PostMapping(value = "/list")
    public Object getUserLevelListApi(@Valid @ModelAttribute LevelListVo levelListVo, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                loger.error(result.getFieldError().getField()+"参数传递的类型错误!");
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }

        if(levelListVo.getPageNum() == 0){
            levelListVo.setPageNum(1);
        }
        if(levelListVo.getPageSize() == 0){
            levelListVo.setPageSize(10);
        }
        PageInfo ReData = userLevelService.getLevelList(levelListVo.getSellerId(),levelListVo.getPageNum(),levelListVo.getPageSize());
        if(ReData.getTotal() > 0l){
            return JsonReturn.SetMsg(0,"获取用户等级列表成功!",ReData);
        }
        return JsonReturn.SetMsg(10011,"暂无用户等级数据!","");
    }

    /**
     * 分配用户等级
     * @param allotLevelVo
     * @param result
     * @return
     */
    @PostMapping(value = "/allot")
    public Object allotUserToLevel(@Valid @ModelAttribute AllotLevelVo allotLevelVo,BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                loger.error(result.getFieldError().getField()+"参数传递的类型错误!");
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }

        int Ok = userLevelService.allotLevelGroup(allotLevelVo.getUserId(),allotLevelVo.getOldLevelId(),allotLevelVo.getToLevelId());
        if(Ok > 0){
            return JsonReturn.SetMsg(0,"分配用户等级成功!","");
        }
        return JsonReturn.SetMsg(10011,"分配用户等级失败!","");
    }

    @PostMapping(value = "/search")
    public Object getLevelUserPage(@Valid @ModelAttribute LevelModuleVo levelModuleVo,BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                loger.error(result.getFieldError().getField()+"参数传递的类型错误!");
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(levelModuleVo.getPageNum() == 0){
            levelModuleVo.setPageNum(1);
        }
        if(levelModuleVo.getPageSize() == 0){
            levelModuleVo.setPageSize(10);
        }
        PageInfo ReData = userLevelService.getLeveUserPage(levelModuleVo.getSellerId(),levelModuleVo.getLevelId(),levelModuleVo.getPageNum(),levelModuleVo.getPageSize());
        if(ReData.getTotal() > 0){
            return JsonReturn.SetMsg(0,"获取数据成功!",ReData);
        }
        return JsonReturn.SetMsg(10011,"当前还暂无用户数据!","");
    }

    @PostMapping(value = "/del")
    public Object delLevelById(@Valid @ModelAttribute DelVo delVo, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                loger.error(result.getFieldError().getField()+"参数传递的类型错误!");
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }

        int Status = userLevelService.delUserLevel(delVo.getLevelId());
        if(Status == -1){
            return JsonReturn.SetMsg(10011,"此等级下包含用户，暂不能删除!","");
        }
        if(Status == 1){
            return JsonReturn.SetMsg(0,"删除等级成功!","");
        }

        return JsonReturn.SetMsg(10011,"删除等级失败!","");

    }

    /**
     * 申请加入供应商
     * @param levelModule
     * @param result
     * @return
     */
    @PostMapping(value = "/add/module")
    public Object addLevelModuleData(@Valid @ModelAttribute LevelModule levelModule,BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                loger.error(result.getFieldError().getField()+"参数传递的类型错误!");
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(UserUtil.getId() == null){
            return JsonReturn.SetMsg(10010,"请登录后在发起申请!","");
        }
        levelModule.setUserId(UserUtil.getId());
        if(UserUtil.getId() == levelModule.getSellerId()){
            return JsonReturn.SetMsg(10010,"不能申请自己成为供应商!","");
        }
        LevelModule maps = new LevelModule();
        maps.setUserId(UserUtil.getId());
        maps.setSellerId(levelModule.getSellerId());
        LevelModule res = usersService.findLevelModuleRow(maps);

        if(res != null){
           return JsonReturn.SetMsg(10010,"您已经申请过此家供应商，不能重复申请!","");
        }
        Byte userType = 1;
        ClientUser seller = usersService.getClienUser(levelModule.getSellerId());
        if(seller == null){
            return JsonReturn.SetMsg(10010,"您申请的供应商不存在，请核对后在发起申请!","");
        }else {
            if(seller.getUserType() != userType){
                return JsonReturn.SetMsg(10010,"您申请的供应商用户不属于商家，不能对普通用户发起供应商申请!","");
            }
        }

        levelModule.setLevelId(0l);
        levelModule.setUserType(userType);
        int Ok = usersService.addLevelModuleData(levelModule);
        if(Ok > 0){
            return JsonReturn.SetMsg(0,"申请供应商请求发送成功，请耐心等待对方审核!","");
        }
        return JsonReturn.SetMsg(10011,"申请供应商请求发送失败，请稍后再试!","");
    }

    @PostMapping(value = "/find/parent")
    public Object findParent(){
        Long userId = UserUtil.getId();
        if(userId == null){
            return JsonReturn.SetMsg(10010,"非法的登陆请求,在请求中无法解析到您的用户编号!","");
        }
        ClientUser clientUser= usersService.getClienUser(userId);
        if(clientUser == null){
            return JsonReturn.SetMsg(10010,"当前用户不存在,您属于非法登陆!","");
        }
        Byte userType = 1;
        if(clientUser.getUserType() != userType){
            return JsonReturn.SetMsg(10010,"只有商家才可以申请供应商哦！","");
        }
        List<ClientUserNameBo> results = usersService.findMyParentUsers(userId);

        if(results.size() > 0){
            return JsonReturn.SetMsg(0,"获取供应商数据成功!",results);
        }
        return JsonReturn.SetMsg(10011,"您还没申请过供应商!","");
    }
}
