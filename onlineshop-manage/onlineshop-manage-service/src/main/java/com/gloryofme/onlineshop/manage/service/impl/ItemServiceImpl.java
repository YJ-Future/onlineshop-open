package com.gloryofme.onlineshop.manage.service.impl;

import com.gloryofme.onlineshop.common.utils.IDUtils;
import com.gloryofme.onlineshop.manage.mapper.ItemDescMapper;
import com.gloryofme.onlineshop.manage.mapper.ItemMapper;
import com.gloryofme.onlineshop.manage.mapper.ItemParamDataMapper;
import com.gloryofme.onlineshop.manage.pojo.Item;
import com.gloryofme.onlineshop.manage.pojo.ItemDesc;
import com.gloryofme.onlineshop.manage.pojo.ItemParamData;
import com.gloryofme.onlineshop.manage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YU on 2016/7/6.
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired(required = false)
    private ItemMapper itemMapper;
    @Autowired(required = false)
    private ItemDescMapper descMapper;
    @Autowired(required = false)
    private ItemParamDataMapper paramDataMapper;

    @Override
    public Item selectById(Long id) {
        return itemMapper.selectById(id);
    }

    @Override
    public List<Item> select(Item item) {
        return itemMapper.select(item);
    }

    @Override
    public Long selectCount() {
        return itemMapper.selectCount();
    }

    @Override
    public List<Item> selectPage(int page, int rows) {
        int start = (page - 1) * rows;
        return itemMapper.selectPage(start, rows);
    }

    @Override
    public int insert(Item item) {
        return itemMapper.insert(item);
    }

    @Override
    public int insertItem(Item item, String desc, String paramData) {
        if(item == null)
            return 0;
        item.setId(IDUtils.generateItemId());
        item.setStatus(1);
        if (itemMapper.insert(item) > 0) {
            ItemDesc itemDesc = new ItemDesc();
            itemDesc.setItemId(item.getId());
            itemDesc.setItemDesc(desc);
            ItemParamData itemParamData = new ItemParamData();
            itemParamData.setItemId(item.getId());
            itemParamData.setParamData(paramData);
            if (descMapper.insert(itemDesc) > 0 && paramDataMapper.insert(itemParamData) > 0)
                return 1;
        }
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return itemMapper.deleteById(id);
    }

    @Override
    public int update(Item item) {
        return itemMapper.update(item);
    }
}
