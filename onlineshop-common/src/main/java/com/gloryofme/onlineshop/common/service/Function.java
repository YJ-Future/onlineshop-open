package com.gloryofme.onlineshop.common.service;

/**
 * 方法回调类
 * Created by YU on 2016/6/30.
 */
public interface Function<E, T> {
    public T callBack(E e);
}
