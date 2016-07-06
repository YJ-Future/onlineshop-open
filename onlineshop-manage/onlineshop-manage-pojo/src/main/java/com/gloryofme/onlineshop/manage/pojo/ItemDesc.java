package com.gloryofme.onlineshop.manage.pojo;

/**
 * Created by YU on 2016/7/1.
 */
public class ItemDesc extends BasePojo {
    //商品ID
    private Long itemId;
    //商品描述
    private String itemDesc;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
}
