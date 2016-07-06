package com.gloryofme.onlineshop.manage.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by YU on 2016/7/4.
 */
public interface PictureService {
    Map uploadPicture(MultipartFile uploadFile);
}
