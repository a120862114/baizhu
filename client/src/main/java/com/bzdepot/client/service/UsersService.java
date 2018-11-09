package com.bzdepot.client.service;

import com.bzdepot.client.mapper.ClientUserMapper;
import com.bzdepot.client.mapper.LevelModuleMapper;
import com.bzdepot.client.mapper.UserLevelMapper;
import com.bzdepot.client.model.ClientUser;
import com.bzdepot.client.model.LevelModule;
import com.bzdepot.client.model.UserLevel;
import com.bzdepot.client.vo.ClientUserNameBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

@Service
public class UsersService {

    private final static Logger loger = LoggerFactory.getLogger(UsersService.class);

    @Autowired
    private ClientUserMapper clientUserMapper;

    @Autowired
    private LevelModuleMapper levelModuleMapper;

    @Autowired
    private UserLevelMapper userLevelMapper;

    @Autowired
    private UserLevelService userLevelService;
    /**
     * 添加用户数据
     * @param clientUser
     * @return
     */
    @Transactional
    public int addClientUser(ClientUser clientUser){
        clientUser.setCreateTime(new Date().getTime());
        clientUser.setUpdateTime(new Date().getTime());
        int Status = 0;
        try {
            Status = clientUserMapper.insertSelective(clientUser);
            if(Status == 0){
                Status = 0;
                throw new RuntimeException("添加终端用户失败!");
            }
            Byte IsDefault = 1;
            if(clientUser.getUserType() != IsDefault) {
                UserLevel userLevel = new UserLevel();
                userLevel.setSellerId(clientUser.getSellerId());
                userLevel.setIsDefault(IsDefault);
                UserLevel DafaultLevel = userLevelMapper.findDefault(userLevel);
                if (DafaultLevel == null) {
                    Status = 0;
                    throw new RuntimeException("获取当前商家默认等级数据失败!");
                }
                LevelModule levelModule = new LevelModule();
                levelModule.setLevelId(DafaultLevel.getId());
                levelModule.setNickname(clientUser.getNickname());
                levelModule.setSellerId(clientUser.getSellerId());
                levelModule.setUserId(clientUser.getUserId());
                Byte UserType = clientUser.getUserType();
                levelModule.setUserType(UserType);
                Status = levelModuleMapper.insertSelective(levelModule);
                if (Status == 0) {
                    Status = 0;
                    throw new RuntimeException("添加等级中间数据失败!");
                }
                //开始计数
                Status = userLevelService.setDafaultNums(DafaultLevel.getId(), DafaultLevel.getNums() + 1);
                if (Status == 0) {
                    Status = 0;
                    throw new RuntimeException("用户等级统计计数失败!");
                }
            }
        }catch (Exception e){
            loger.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Status;
    }

    /**
     * 添加等级映射中间表
     * @param levelModule
     * @return
     */
    public int addLevelModuleData(LevelModule levelModule){

        return levelModuleMapper.insertSelective(levelModule);
    }

    /**
     * 根据userid 与 sellerid查询
     */
    public LevelModule findLevelModuleRow(LevelModule maps){
        LevelModule res = levelModuleMapper.findLevelIdByUserIdAndSellerId(maps);
        return res;
    }
    /**
     * 查询用户的供应商信息
     */
    public List<ClientUserNameBo> findMyParentUsers(Long userId){
        LevelModule maps = new LevelModule();
        maps.setUserId(userId);
        List<ClientUserNameBo> resData = levelModuleMapper.findMyParentUserData(maps);
        return resData;
    }
    /**
     * 通过用户编号获取他的等级数据
     * @param id
     * @return
     */
   public ClientUser getClienUser (long id){
       ClientUser clientUser = clientUserMapper.getClientUser(id);
       if(clientUser == null){
           return null;
       }
       LevelModule levelModule = new LevelModule();
       levelModule.setSellerId(clientUser.getSellerId());
       levelModule.setUserId(clientUser.getUserId());
       LevelModule levelResult = levelModuleMapper.findLevelIdByUserIdAndSellerId(levelModule);
       if(levelResult == null){
           return clientUser;
       }
       clientUser.setLevelId(levelResult.getLevelId());
       return clientUser;
    }

}
