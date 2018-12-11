package com.bzdepot.images.service.impl;

import com.bzdepot.images.mapper.ImagesMapper;
import com.bzdepot.images.model.Images;
import com.bzdepot.images.service.ImagesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ImagesServiceImpl implements ImagesService {

    @Autowired
    private ImagesMapper imgMaper;

    /*
    *   添加图片到数据库中
    * */
    @Override
    public Long addImages(Images images){
        //设置时间戳
        Long NowTime = new Date().getTime();
        images.setCreateTime(NowTime);
        images.setUpdateTime(NowTime);
        imgMaper.insertSelective(images);
        return images.getId();
    }

    /*
    *   通过MD5获得当前添加的图片是否已经存在（秒传文件）
    *   如果此文件已经存在那么直接复制一条数据到当前用户名下
    * */
    @Override
    public Images findImagesByMd5(String md5,Long user_id){
        //如果当前图片已经存在于当前用户数据仓库中则直接返回
        List<Images> ResultData = imgMaper.findImagesByMd5(md5,user_id);
        if(ResultData != null && ResultData.size() > 0){
            return ResultData.get(0);
        }
        List<Images> AllResultData = imgMaper.findImagesByMd5(md5,null);
        if(AllResultData != null && AllResultData.size() > 0){
            Images ImgData = AllResultData.get(0);
            ImgData.setId(null);
            ImgData.setUserId(user_id);
            Long ImgId = this.addImages(ImgData);
            if(ImgId != null){
                return ImgData;
            }
        }
        return null;
    }
    /*
    * 分页查询当前用户所有图片
    * */
   public PageInfo<List<Images>> searchImagesForUserPage(Long UserId,String ImgName,Long StatTime, Long EndTime,int pageNum,int pageSize){
       PageHelper.startPage(pageNum,pageSize);
       List<Images> PageData = imgMaper.selectUserImgPage(UserId,ImgName,StatTime,EndTime);
       PageInfo<List<Images>> pageInfo = null;
       if(PageData != null){
           pageInfo = new PageInfo(PageData);
       }
       return pageInfo;
   }
}
