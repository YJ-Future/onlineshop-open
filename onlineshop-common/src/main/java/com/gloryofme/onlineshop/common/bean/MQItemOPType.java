package com.gloryofme.onlineshop.common.bean;

/**
 * Created by YU on 2016/7/9.
 */
public enum MQItemOPType {

    add(0, "add"),
    update(1, "update"),
    delete(2, "delete");

    private String title;
    private int value;

    MQItemOPType(int value, String title) {
        this.value = value;
        this.title = title;
    }
}
