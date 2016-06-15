package com.gloryofme.onlineshop.manage.mapper;

import com.gloryofme.onlineshop.manage.pojo.Item;
import com.gloryofme.onlineshop.manage.pojo.ItemArgs;

import java.util.List;

/**商品Mapper
 * Created by YU on 2016/6/9.
 */
public interface  ItemMapper {

    Item  selectById(Long id);
    List<Item> selectByArgs(ItemArgs args);
    int insert(Item item);
    int deleteById(Long id);
    int deleteByArgs(ItemArgs args);
    int update(Item item);
}
