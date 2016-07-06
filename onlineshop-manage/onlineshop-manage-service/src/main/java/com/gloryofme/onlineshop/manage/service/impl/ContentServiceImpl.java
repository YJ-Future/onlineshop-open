package com.gloryofme.onlineshop.manage.service.impl;

import com.gloryofme.onlineshop.manage.mapper.ContentMapper;
import com.gloryofme.onlineshop.manage.pojo.Content;
import com.gloryofme.onlineshop.manage.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YU on 2016/7/6.
 */
@Service
public class ContentServiceImpl implements ContentService{

    @Autowired(required = false)
    private ContentMapper contentMapper;

    @Override
    public Content selectById(Long id) {
        return null;
    }

    @Override
    public List<Content> select(Content content) {
        return null;
    }

    @Override
    public Long selectCount() {
        return null;
    }

    @Override
    public List<Content> selectPage(int page, int rows) {
        return null;
    }

    @Override
    public List<Content> selectPageByCatId(Long categoryId, int page, int rows) {
        int start = (page - 1)*rows;
        return contentMapper.selectPageByCatId(categoryId, start, rows);
    }

    @Override
    public int insert(Content content) {
        return contentMapper.insert(content);
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public int update(Content content) {
        return 0;
    }
}
