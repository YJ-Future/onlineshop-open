package com.gloryofme.onlineshop.manage.service;

import com.gloryofme.onlineshop.manage.pojo.ItemCategoryResult;

import java.util.List;

/**
 * Created by YU on 2016/7/2.
 */
public interface ItemCategoryService {

    ItemCategoryResult selectAllItemCatById(Long id);
}
