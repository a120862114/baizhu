package com.bzdepot.offer.service;

import com.bzdepot.offer.mapper.ClassGroupMapper;
import com.bzdepot.offer.mapper.ClassfiyMapper;
import com.bzdepot.offer.model.ClassGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClassGroupService {

    @Autowired
    private ClassGroupMapper classGroupMapper;

    @Autowired
    private ClassfiyMapper classfiyMapper;

    public Long addClassGroup(ClassGroup classGroup){
        classGroup.setCreateTime(new Date().getTime());
        classGroup.setUpdateTime(new Date().getTime());
        Byte Status = 1;
        classGroup.setStatus(Status);
        classGroupMapper.insertSelective(classGroup);
        return classGroup.getId();
    }

    /*
    * 编辑分类的分组信息
    * */
    public int updateClassGroup(ClassGroup classGroup){
        classGroup.setUpdateTime(new Date().getTime());
       return classGroupMapper.updateByPrimaryKeySelective(classGroup);
    }

    /*
    *  关联查询获取分组与分类信息
    * */
    public List<ClassGroup> findAllClassGroupAndJoin(Long SellerId){
        List<ClassGroup> ResultData = classGroupMapper.selectGroupAndClass(SellerId);
        return ResultData;
    }

    /*
    *  删除分组数据(重要提示：删除只能删除为空的分组)
    * */
    public int delEmptyGroupById(Long id){
        Long CountNum = classfiyMapper.countClassfiyForFroup(id);
        Long nums = 0L;
        System.out.println(CountNum);
        System.out.println(CountNum.equals(nums));
        if(!CountNum.equals(nums)){
            return -1;
        }
        return classGroupMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询单条的分组数据
     * @param sellerId
     * @param groupName
     * @return
     */
    public ClassGroup findClassGroupByGroupName(Long sellerId,String groupName){
        return classGroupMapper.selectByGroupNameAndSellerId(sellerId,groupName);
    }
}
