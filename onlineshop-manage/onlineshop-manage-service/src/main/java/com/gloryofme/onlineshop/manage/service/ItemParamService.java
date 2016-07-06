package com.gloryofme.onlineshop.manage.service;

import com.gloryofme.onlineshop.manage.pojo.ItemParam;

import java.util.List;

/**
 * Created by YU on 2016/7/5.
 */
public interface ItemParamService extends BaseService<ItemParam>{

    List<ItemParam> selectByItemCatId(Long itemCatId);

}
