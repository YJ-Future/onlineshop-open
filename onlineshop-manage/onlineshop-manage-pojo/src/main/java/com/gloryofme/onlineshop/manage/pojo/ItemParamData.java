package com.gloryofme.onlineshop.manage.pojo;

/**
 * 商品对应的参数具体内容
 * Created by YU on 2016/7/6.
 */
public class ItemParamData extends BasePojo{
    private Long id;
    private Long itemId;
    private String paramData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData;
    }
}
