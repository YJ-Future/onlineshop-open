package com.gloryofme.onlineshop.manage.mapper;

import com.gloryofme.onlineshop.manage.pojo.ItemDesc;
import org.apache.ibatis.annotations.Param;

/**
 * Created by YU on 2016/7/6.
 */
public interface ItemDescMapper extends BaseMapper<ItemDesc>{
    ItemDesc selectByItemId(Long itemId);
    int deleteByItemId(Long itemId);
}
