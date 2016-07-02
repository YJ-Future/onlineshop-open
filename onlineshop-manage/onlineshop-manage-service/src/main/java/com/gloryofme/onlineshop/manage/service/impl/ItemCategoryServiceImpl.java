package com.gloryofme.onlineshop.manage.service.impl;

import com.gloryofme.onlineshop.manage.mapper.ItemCategoryMapper;
import com.gloryofme.onlineshop.manage.pojo.ItemCategory;
import com.gloryofme.onlineshop.manage.pojo.ItemCategoryResult;
import com.gloryofme.onlineshop.manage.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *类目实现类
 * Created by YU on 2016/7/2.
 */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService{
    @Autowired(required = false)
    private ItemCategoryMapper categoryMapper;

    /**
     * 获取ID下对应的所有类目
     * @param id
     * @return
     */
    @Override
    public ItemCategoryResult selectAllItemCatById(Long id) {
        ItemCategoryResult categroy = null;
        if(id == 0){
            categroy = new ItemCategoryResult();
        }else
            categroy= categoryMapper.selectById(id);
        List<ItemCategoryResult> children = categoryMapper.selectChildrenById(id);
        categroy.setChildren(children);
        if(children != null){
            for(ItemCategoryResult cat : children){
                    cat.setChildren(selectAllItemCatById(cat.getId()).getChildren());
            }
        }
        return categroy;
    }
}
