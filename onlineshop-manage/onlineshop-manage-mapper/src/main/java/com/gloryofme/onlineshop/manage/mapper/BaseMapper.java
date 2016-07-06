package com.gloryofme.onlineshop.manage.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by YU on 2016/7/5.
 */
public interface BaseMapper<T> {
    T selectById(Long id);
    T select(T t);
    Long selectCount();
    List<T> selectPage(@Param("start") int start, @Param("size") int rows);
    int insert(T t);
    int deleteById(@Param("id") Long id);
    int update(T t);
}
