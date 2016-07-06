package com.gloryofme.onlineshop.manage.mapper;

import com.gloryofme.onlineshop.manage.pojo.ItemParam;

import java.util.List;

/**
 * Created by YU on 2016/7/5.
 */
public interface ItemParamMapper extends BaseMapper<ItemParam>{

     List<ItemParam> selectByItemCatId(Long itemCatId);


}
