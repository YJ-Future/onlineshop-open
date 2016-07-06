package com.gloryofme.onlineshop.manage.service;

import com.gloryofme.onlineshop.manage.pojo.ItemCatResult;
import com.gloryofme.onlineshop.manage.pojo.ItemCategory;
import com.gloryofme.onlineshop.manage.pojo.ItemCategoryResult;

import java.util.List;

/**
 * Created by YU on 2016/7/2.
 */
public interface ItemCategoryService {

    ItemCatResult selectAllItemCat();
    ItemCategoryResult selectAllItemCatById(Long id);
     List<ItemCategory> selectItemCategoryList(Long parentId);
}
