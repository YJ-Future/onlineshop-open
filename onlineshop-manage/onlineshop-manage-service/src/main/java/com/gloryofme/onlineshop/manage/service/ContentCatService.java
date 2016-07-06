package com.gloryofme.onlineshop.manage.service;

import com.gloryofme.onlineshop.manage.pojo.ContentCategory;

import java.util.List;

/**
 * Created by YU on 2016/7/6.
 */
public interface ContentCatService extends BaseService<ContentCategory>{

    List<ContentCategory> selectContentCatList(Long parentId);
}
