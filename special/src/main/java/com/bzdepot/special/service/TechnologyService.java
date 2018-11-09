package com.bzdepot.special.service;

import com.bzdepot.common.util.UserUtil;
import com.bzdepot.special.bo.TechnologyPost;
import com.bzdepot.special.mapper.TechnologyAttrMapper;
import com.bzdepot.special.mapper.TechnologyClassMapper;
import com.bzdepot.special.model.TechnologyAttr;
import com.bzdepot.special.model.TechnologyClass;
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
public class TechnologyService {

    private final static Logger loger = LoggerFactory.getLogger(TechnologyService.class);

    @Autowired
    private TechnologyClassMapper technologyClassMapper; //工艺分类

    @Autowired
    private TechnologyAttrMapper technologyAttrMapper; //工艺属性

    /**
     * 更新工艺分类与工艺属性
     * @param technologyBo
     * @return
     */
    @Transactional
    public int updateTechnology(TechnologyPost technologyBo){
        int Ok = 0;
        try {
            for (int i = 0; i < technologyBo.getClasses().length; i++){
                TechnologyClass technologyClass = technologyBo.getClasses()[i];
                if(UserUtil.getId() != null){
                    technologyClass.setSellerId(UserUtil.getId());
                }
                technologyClass.setCreateTime(new Date().getTime());
                technologyClass.setUpdateTime(new Date().getTime());

                if(technologyClass.getId() == null){
                    Ok = technologyClassMapper.insertSelective(technologyClass);
                }else{
                    Ok = technologyClassMapper.updateByPrimaryKeySelective(technologyClass);
                }
                if(Ok == 0){
                    break;
                }
                System.out.println("------------------------------------------");
               System.out.println(technologyClass.toString());
                System.out.println("===========================================");
                if(technologyClass.getDetail() != null){
                        for(int s = 0; s < technologyClass.getDetail().length; s++){
                            TechnologyAttr technologyAttr = technologyClass.getDetail()[s];
                            System.out.println(technologyAttr.toString());
                            //设置所属材质ID编号
                            if(technologyClass.getId() != null){
                                technologyAttr.settId(technologyClass.getId());
                            }
                            if(UserUtil.getId() != null){
                                technologyAttr.setSellerId(UserUtil.getId());
                            }

                            technologyAttr.setCreateTime(new Date().getTime());
                            technologyAttr.setUpdateTime(new Date().getTime());
                            if(technologyAttr.getId() == null){
                                Ok = technologyAttrMapper.insertSelective(technologyAttr);
                            }else{
                                Ok = technologyAttrMapper.updateByPrimaryKeySelective(technologyAttr);
                            }
                            if(Ok == 0){
                                break;
                            }
                        }
                }
            }
            if(Ok == 0){
                throw new RuntimeException("更是工艺失败!");
            }
        }catch (Exception e){
            loger.error("工艺更新错误信息："+e.toString());
            loger.error("更新工艺失败，事务回滚!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Ok;
    }

    /**
     * 删除材质分类和材质属性
     * @param classId
     * @return
     */
    @Transactional
    public int delClassAndAttr(Long classId){
        int Ok = 0;
        try {
            Ok = technologyClassMapper.deleteByPrimaryKey(classId);
            if(Ok > 0){
                Ok = technologyAttrMapper.deleteByTid(classId);
            }
            if(Ok == 0){
                throw new RuntimeException("删除失败!");
            }
        }catch (Exception e){
            loger.error("删除材质相关失败，事务回滚!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Ok;
    }

    /**
     * 查询材质分类
     * @param id
     * @return
     */
    public TechnologyClass findTechnologyClass(Long id){
        return technologyClassMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询材质属性
     * @param id
     * @return
     */
    public TechnologyAttr findTechnologyAttr(Long id){
        return technologyAttrMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除材质属性
     * @param id
     * @return
     */
    public int deleteAttr(Long id,Long tId){
        if(technologyAttrMapper.deleteByPrimaryKey(id) > 0){
            if(technologyAttrMapper.countAttrDataByTid(UserUtil.getId(),tId) == 0){
                return technologyClassMapper.deleteByPrimaryKey(tId);
            }
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 查询列表
     * @return
     */
    public PageInfo pageListData(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<TechnologyClass> technologyClasses = technologyClassMapper.selectPageClassAndAttr(UserUtil.getId());
        PageInfo pageInfo = new PageInfo(technologyClasses);
        return pageInfo;
    }
}
