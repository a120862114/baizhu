package com.bzdepot.images.service;

import com.bzdepot.images.model.Images;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ImagesService {

    Long addImages(Images images);

    Images findImagesByMd5(String md5,Long user_id);

    PageInfo<List<Images>> searchImagesForUserPage(Long UserId, String ImgName, Long StatTime, Long EndTime, int pageNum, int pageSize);

}
