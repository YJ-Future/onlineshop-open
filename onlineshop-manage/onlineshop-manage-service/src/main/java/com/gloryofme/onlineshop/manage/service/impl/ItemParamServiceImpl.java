package com.gloryofme.onlineshop.manage.service.impl;

import com.gloryofme.onlineshop.manage.mapper.ItemParamMapper;
import com.gloryofme.onlineshop.manage.pojo.ItemParam;
import com.gloryofme.onlineshop.manage.service.BaseService;
import com.gloryofme.onlineshop.manage.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YU on 2016/7/5.
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired(required = false)
    private ItemParamMapper itemParamMapper;

    @Override
    public List<ItemParam> selectByItemCatId(Long itemCatId) {
        return itemParamMapper.selectByItemCatId(itemCatId);
    }

    @Override
    public List<ItemParam> selectPage(int page, int rows) {
        int start = (page-1)*rows;
        return itemParamMapper.selectPage(start, rows);
    }

    @Override
    public ItemParam selectById(Long id) {
        return itemParamMapper.selectById(id);
    }

    @Override
    public ItemParam select(ItemParam itemParam) {
        return itemParamMapper.select(itemParam);
    }

    @Override
    public Long selectCount() {
        return itemParamMapper.selectCount();
    }

    @Override
    public int insert(ItemParam itemParam) {
        return itemParamMapper.insert(itemParam);
    }

    @Override
    public int deleteById(Long id) {
        return itemParamMapper.deleteById(id);
    }

    @Override
    public int update(ItemParam itemParam) {
        return itemParamMapper.update(itemParam);
    }
}
