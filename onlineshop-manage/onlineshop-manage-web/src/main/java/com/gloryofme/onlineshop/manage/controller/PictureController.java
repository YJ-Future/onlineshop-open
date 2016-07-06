package com.gloryofme.onlineshop.manage.controller;

import com.gloryofme.onlineshop.manage.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by YU on 2016/7/4.
 */
@RequestMapping("/pic")
@Controller
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/upload")
    @ResponseBody
    public Map picUpload(MultipartFile uploadFile) {
        return pictureService.uploadPicture(uploadFile);
    }
}
