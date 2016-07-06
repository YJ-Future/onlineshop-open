package com.gloryofme.onlineshop.manage.pojo;

/**商品参数类
 * Created by YU on 2016/6/9.
 */
public class ItemParam extends BasePojo {
    private Long id;

    private Long itemCatId;

    private String paramData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemCatId() {
        return itemCatId;
    }

    public void setItemCatId(Long itemCatId) {
        this.itemCatId = itemCatId;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData;
    }
}
