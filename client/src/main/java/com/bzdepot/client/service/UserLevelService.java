package com.bzdepot.client.service;

import com.bzdepot.client.mapper.LevelModuleMapper;
import com.bzdepot.client.mapper.UserLevelMapper;
import com.bzdepot.client.model.LevelModule;
import com.bzdepot.client.model.UserLevel;
import com.bzdepot.client.vo.ClientUserNameBo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

@Service
public class UserLevelService {

    private final static Logger loger = LoggerFactory.getLogger(UserLevelService.class);

    @Autowired
    private UserLevelMapper userLeveMapper;

    @Autowired
    private LevelModuleMapper levelModuleMapper;
    /**
     * 添加用户等级组
     * @param userLevel
     * @return
     */
    public Long addUserLevel(UserLevel userLevel){
        userLevel.setCreateTime(new Date().getTime());
        userLevel.setUpdateTime(new Date().getTime());
        userLeveMapper.insertSelective(userLevel);
        return userLevel.getId();
    }

    /**
     * 修改用户等级组
     * @param userLevel
     * @return
     */
    public int editUserLevel(UserLevel userLevel){
        userLevel.setUpdateTime(new Date().getTime());
        return userLeveMapper.updateByPrimaryKeySelective(userLevel);
    }

    /**
     * 删除等级组
     * @param id
     * @return
     */
    public int delUserLevel(Long id){
       Long nums = levelModuleMapper.countLevelUserNums(id);
       if(nums == 0l){
           return userLeveMapper.deleteByPrimaryKey(id);
       }
       return -1;
    }

    /**
     * 分页获取用户等级
     * @param sellerId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo getLevelList(Long sellerId ,int pageNum,int pageSize){
            PageHelper.startPage(pageNum,pageSize);
            List<UserLevel> PageData = userLeveMapper.getList(sellerId);
            PageInfo pageInfo = new PageInfo(PageData);
            return pageInfo;
    }

    /**
     * 设置用户等级计数
     * @param id
     * @param nums
     * @return
     */
    public int setDafaultNums(Long id,Integer nums){
        UserLevel userLevel = new UserLevel();
        userLevel.setUpdateTime(new Date().getTime());
        userLevel.setNums(nums);
        userLevel.setId(id);
        return userLeveMapper.updateByPrimaryKeySelective(userLevel);
    }

    /**
     * 分配用户等级
     * @param UserId
     * @param OldLevelId
     * @param ToLevelId
     * @return
     */
    @Transactional
    public int allotLevelGroup(Long UserId,Long OldLevelId,Long ToLevelId){
        int Status = 0;
        LevelModule levelModule = new LevelModule();
        levelModule.setUserId(UserId);
        levelModule.setLevelId(ToLevelId);
        try {
            UserLevel OldLevel = null;
            if(OldLevelId != 0l){
                OldLevel = userLeveMapper.selectByPrimaryKey(OldLevelId);
                if(OldLevel == null){
                    Status = 0;
                    throw new RuntimeException("旧等级不能存在");
                }
            }
            UserLevel NewLevel = userLeveMapper.selectByPrimaryKey(ToLevelId);
            if(NewLevel == null){
                Status = 0;
                throw new RuntimeException("新等级不能存在");
            }
            Status = levelModuleMapper.updateLevelIdByUserIdSelective(levelModule);
            if(Status == 0){
                throw new RuntimeException("重新分配用户等级失败");
            }
            if(OldLevelId != 0l) {
                Status = this.setDafaultNums(OldLevelId, OldLevel.getNums() - 1);
                if (Status == 0) {
                    throw new RuntimeException("旧等级减数量失败");
                }
            }
            Status = this.setDafaultNums(ToLevelId,NewLevel.getNums() + 1);
            if(Status == 0){
                throw new RuntimeException("新等级加数量失败");
            }
        }catch (Exception e){
            loger.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Status;
    }

    /**
     * 根据等级获取用户数据
     * @param sellerId
     * @param levelId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo getLeveUserPage(Long sellerId,Long levelId,int pageNum,int pageSize){
        LevelModule levelModule = new LevelModule();
        levelModule.setSellerId(sellerId);
        levelModule.setLevelId(levelId);
        PageHelper.startPage(pageNum,pageSize);
        List<ClientUserNameBo> PageData = levelModuleMapper.searchLevelUserPage(levelModule);
        PageInfo pageInfo = new PageInfo(PageData);
        return pageInfo;
    }

    /**
     * 根据等级名称查找当前商家下的会员等级数据
     * @param sellerId
     * @param levelName
     * @return
     */
    public UserLevel findUserLevelBySellerIdAndLevelName(Long sellerId,String levelName){
        return userLeveMapper.selectBySellerIdAndLevelName(sellerId,levelName);
    }
}
