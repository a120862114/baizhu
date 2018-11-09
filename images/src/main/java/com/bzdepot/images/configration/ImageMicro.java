package com.bzdepot.images.configration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
*  图片上传服务的相关配置
* */
@Data
@Component
@ConfigurationProperties(prefix="images")
public class ImageMicro {

    private String server_host; //上传图片的主机

    private int server_port; //上传图片的端口

    private Long image_size; //上传图片大小的限制

    private String[] image_ext; //上传图片的格式限制

}
