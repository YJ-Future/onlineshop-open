package com.gloryofme.onlineshop.manage.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YU on 2016/7/4.
 */
public class ItemCatResult {

    @JsonProperty("data")
    private List<ItemCatData> itemCats = new ArrayList<>();

    public List<ItemCatData> getItemCats() {
        return itemCats;
    }

    public void setItemCats(List<ItemCatData> itemCats) {
        this.itemCats = itemCats;
    }
}
