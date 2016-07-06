package com.gloryofme.onlineshop.manage.service.impl;

import com.gloryofme.onlineshop.manage.mapper.ContentCatMapper;
import com.gloryofme.onlineshop.manage.pojo.ContentCategory;
import com.gloryofme.onlineshop.manage.service.ContentCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YU on 2016/7/6.
 */
@Service
public class ContentCatServiceImpl implements ContentCatService{
    @Autowired(required = false)
    private ContentCatMapper contentCatMapper;

    @Override
    public ContentCategory selectById(Long id) {
        return contentCatMapper.selectById(id);
    }

    @Override
    public List<ContentCategory> select(ContentCategory contentCategory) {
        return contentCatMapper.select(contentCategory);
    }

    @Override
    public List<ContentCategory> selectContentCatList(Long parentId) {
        ContentCategory contentCategory = new ContentCategory();
        contentCategory.setParentId(parentId);
        return contentCatMapper.select(contentCategory);
    }

    @Override
    public Long selectCount() {
        return null;
    }

    @Override
    public List<ContentCategory> selectPage(int page, int rows) {
        return null;
    }

    @Override
    public int insert(ContentCategory contentCategory) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public int update(ContentCategory contentCategory) {
        return 0;
    }
}
