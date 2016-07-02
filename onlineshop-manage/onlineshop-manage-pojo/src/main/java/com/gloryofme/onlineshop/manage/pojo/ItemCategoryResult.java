package com.gloryofme.onlineshop.manage.pojo;

import java.util.List;

/**
 * Created by YU on 2016/7/2.
 */
public class ItemCategoryResult extends ItemCategory{

    private List<ItemCategoryResult> children;

    public List<ItemCategoryResult> getChildren() {
        return children;
    }

    public void setChildren(List<ItemCategoryResult> children) {
        this.children = children;
    }
}
