package com.gloryofme.onlineshop.manage.mapper;

import com.gloryofme.onlineshop.manage.pojo.Item;
import com.gloryofme.onlineshop.manage.pojo.ItemCategory;
import com.gloryofme.onlineshop.manage.pojo.ItemCategoryResult;

import java.util.List;

/**
 * Created by YU on 2016/7/1.
 */
public interface ItemCategoryMapper {
    //根据类目ID获取类目
    ItemCategoryResult selectById(Long id);
    //根据类目部门信息查找类目全部信息
    List<ItemCategory> select(ItemCategory category);
    //根据类目ID获取子类目 往下找一级子类目
    List<ItemCategoryResult> selectChildrenById(Long id);
    int insert(Item item);
    int deleteById(Long id);
    int update(Item item);
}
