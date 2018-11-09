package com.bzdepot.images.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.common.util.Md5Encode;
import com.bzdepot.images.configration.ImageMicro;
import com.bzdepot.images.model.Images;
import com.bzdepot.images.service.ImagesService;
import com.bzdepot.images.vo.ListVo;
import com.github.pagehelper.PageInfo;
import net.anumbrella.seaweedfs.core.FileSource;
import net.anumbrella.seaweedfs.core.FileTemplate;
import net.anumbrella.seaweedfs.core.file.FileHandleStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/images/file")
public class ImagesController {

    private final static Logger loger = LoggerFactory.getLogger(ImagesController.class);

    @Autowired
    private ImagesService imagesService;

    @Autowired
    private ImageMicro imgConf;

    @PostMapping(value = "/upload")
    public Object UploadImages(@ModelAttribute("image") MultipartFile image, HttpServletRequest request) {
        if (image == null || image.isEmpty()) {
            loger.error("图片不能为空");
            return JsonReturn.SetMsg(10010, "没有需要上传的图片!", "");
        }
        String fileName = image.getOriginalFilename();
        String Types = image.getContentType();
        String Names = image.getName();
        String UserId = request.getParameter("user_id");
        if(UserId == null){
            return  JsonReturn.SetMsg(10010,"文件所属用户编号不能为空!","");
        }
        //获取上传文件的后缀名
        String FileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
        //验证图片的上传格式是否合法
        String[] extRange = imgConf.getImage_ext();
        boolean Ok = Arrays.asList(extRange).contains(FileExt);
        if(Ok == false){
            return JsonReturn.SetMsg(10010,"不能上传"+FileExt+"格式的文件!","");
        }
        Long UserIdLong = null;
        try {
            UserIdLong = Long.parseLong(UserId);
        }catch (NumberFormatException e){
            return  JsonReturn.SetMsg(10010,"用户编号转码失败!",null);
        }
        if(UserIdLong == null){
            return JsonReturn.SetMsg(10010,"用户编号转码后为空!","");
        }

        //计算上传文件不能超过指定的大小
        Long UtilNum = 1024L*1024L*imgConf.getImage_size();
        if(UtilNum < image.getSize()){
            return JsonReturn.SetMsg(10010,"上传文件不能超过2MB","");
        }
        String ImageMd5 = null;
        try {
            ImageMd5 = Md5Encode.getFileMD5String(image.getInputStream());
            if(ImageMd5 == null){
                return JsonReturn.SetMsg(10010,"获取MD5效验值失败!","");
            }
        } catch (IOException e) {
            loger.error(e.getMessage());
            return JsonReturn.SetMsg(10010,"获取MD5效验值失败!","");
        }
        Images ImgResult = imagesService.findImagesByMd5(ImageMd5,UserIdLong);
        if(ImgResult != null){
            Map<String,Object> Datas = new HashMap<String, Object>();
            Datas.put("img_id",ImgResult.getImgId());
            Datas.put("img_size",ImgResult.getSize());
            Datas.put("img_host",imgConf.getServer_host());
            Datas.put("img_port",imgConf.getServer_port());
            Datas.put("img_ext",ImgResult.getExt());
            return JsonReturn.SetMsg(0,"图片上传成功",Datas);
        }
        //准备上传到分布式文件存储系统
        FileSource fileSource = null;
        try {
            fileSource  = new FileSource();
            fileSource.setHost(imgConf.getServer_host());
            fileSource.setPort(imgConf.getServer_port());
            fileSource.setConnectionTimeout(5000);
            fileSource.startup();
        }catch (IOException e){
            loger.error(e.getMessage());
            return JsonReturn.SetMsg(10011,"图片服务器错误!","");
        }
        FileHandleStatus status = null;
        try {
            FileTemplate template = new FileTemplate(fileSource.getConnection());
            template.setUsingPublicUrl(false);
            status = template.saveFileByStream("file", image.getInputStream());
        }catch (IOException e){
            loger.error(e.getMessage());
            return JsonReturn.SetMsg(10011,"图片读写错误!","");
        }

        if(status == null){
            return JsonReturn.SetMsg(10011,"上传图片失败!","");
        }
        if(status.getFileId() == null){
            return JsonReturn.SetMsg(10011,"上传图片失败!","");
        }
        Map<String,Object> Datas = new HashMap<String, Object>();
        Datas.put("img_id",status.getFileId());
        Datas.put("img_size",status.getSize());
        Datas.put("img_host",imgConf.getServer_host());
        Datas.put("img_port",imgConf.getServer_port());
        Datas.put("img_ext",FileExt);
        //将图片信息存储到当前用户的数据仓库中
        Images ImgModel = new Images();
        ImgModel.setUserId(UserIdLong);
        ImgModel.setImgId(status.getFileId());
        ImgModel.setSize(status.getSize());
        ImgModel.setExt(FileExt);
        ImgModel.setMime(Types);
        ImgModel.setMd5(ImageMd5);
        ImgModel.setImgName(fileName);
        Long ImgID = imagesService.addImages(ImgModel);
        if(ImgID == null){
            return JsonReturn.SetMsg(10011,"图片存储失败!","");
        }
        return JsonReturn.SetMsg(0,"上传图片成功!",Datas);
    }

    /*
    *   用户分页图片列表
    * */
    @PostMapping(value = "/list")
    public Object ImgList(@Valid  @ModelAttribute ListVo vo, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if (vo.getPagesize() == 0){
            vo.setPagesize(14);
        }
        if(vo.getPagenum() == 0){
            vo.setPagenum(1);
        }
        PageInfo ReData = imagesService.searchImagesForUserPage(vo.getUser_id(),vo.getImg_name(),vo.getStarttime(),vo.getEndtime(),vo.getPagenum(),vo.getPagesize());
        Map<String,Object> Datas = new HashMap<String, Object>();
        Datas.put("data",ReData);
        Datas.put("host",imgConf.getServer_host());
        Datas.put("port",imgConf.getServer_port());
        return JsonReturn.SetMsg(0,"获取成功!",Datas);
    }

}
