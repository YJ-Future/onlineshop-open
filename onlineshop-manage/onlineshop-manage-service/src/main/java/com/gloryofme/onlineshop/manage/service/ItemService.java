package com.gloryofme.onlineshop.manage.service;

import com.gloryofme.onlineshop.manage.pojo.Item;

/**
 * Created by YU on 2016/6/9.
 */
public interface ItemService extends BaseService<Item>{

    int insertItem(Item item, String desc, String paramData);
}
