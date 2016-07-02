package com.gloryofme.onlineshop.manage.mapper;

import com.gloryofme.onlineshop.manage.pojo.Item;
import com.gloryofme.onlineshop.manage.pojo.ItemParam;

import java.util.List;

/**商品Mapper
 * Created by YU on 2016/6/9.
 */
public interface  ItemMapper {

    Item  selectById(Long id);
    List<Item> selectByArgs(ItemParam args);
    int insert(Item item);
    int deleteById(Long id);
    int deleteByArgs(ItemParam args);
    int update(Item item);
}
