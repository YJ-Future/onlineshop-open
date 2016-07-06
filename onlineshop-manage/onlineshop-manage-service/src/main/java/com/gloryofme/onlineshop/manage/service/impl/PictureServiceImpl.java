package com.gloryofme.onlineshop.manage.service.impl;

import com.gloryofme.onlineshop.common.utils.FtpUtil;
import com.gloryofme.onlineshop.common.utils.IDUtils;
import com.gloryofme.onlineshop.manage.service.PictureService;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YU on 2016/7/4.
 */
@Service
public class PictureServiceImpl implements PictureService{
    @Value("${ftp.host}")
    private String host;
    @Value("${ftp.port}")
    private int port;
    @Value("${ftp.username}")
    private String username;
    @Value("${ftp.password}")
    private String password;
    @Value("${ftp.base.path}")
    private String basePath;
    @Value("${image.base.url}")
    private String imageBaseUrl;

    @Override
    public Map uploadPicture(MultipartFile uploadFile) {
        Map resultMap = new HashMap<>();
        String originalName = uploadFile.getOriginalFilename();
        Long imageId = IDUtils.generateImageId();
        String imageName = imageId + originalName.substring(originalName.lastIndexOf("."));
        String dateUrl = (new DateTime()).toString("/yyyy/MM/dd");
        boolean result = false;
        try {
            result = FtpUtil.uploadFile(host,port,username,password,basePath,dateUrl,imageName,uploadFile.getInputStream());
            if(!result){
                resultMap.put("error",1);
                resultMap.put("message","图片上传失败");
            }else{
                resultMap.put("error",0);
                resultMap.put("url",imageBaseUrl + dateUrl + "/" + imageName);
            }
        } catch (IOException e) {
            resultMap.put("error",1);
            resultMap.put("message","图片上传发生异常");
        }
        return resultMap;
    }
}
