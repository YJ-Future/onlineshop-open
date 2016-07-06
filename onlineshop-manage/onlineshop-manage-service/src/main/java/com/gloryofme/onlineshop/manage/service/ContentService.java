package com.gloryofme.onlineshop.manage.service;

import com.gloryofme.onlineshop.manage.pojo.Content;

import java.util.List;

/**
 * Created by YU on 2016/7/6.
 */
public interface ContentService extends BaseService<Content>{

    List<Content> selectPageByCatId(Long categoryId, int page, int rows);
}
