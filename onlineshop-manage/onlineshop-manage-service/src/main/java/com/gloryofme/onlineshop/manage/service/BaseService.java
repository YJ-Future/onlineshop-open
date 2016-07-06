package com.gloryofme.onlineshop.manage.service;

import java.util.List;

/**
 * Created by YU on 2016/7/5.
 */
public interface BaseService<T> {
    T selectById(Long id);
    List<T> select(T t);
    Long selectCount();
    List<T> selectPage(int page, int rows);
    int insert(T t);
    int deleteById(Long id);
    int update(T t);
}
