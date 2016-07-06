package com.gloryofme.onlineshop.manage.pojo;

/**
 *商品类目
 * Created by YU on 2016/7/1.
 */
public class ItemCategory extends BasePojo{
    //商品类目ID
    private Long id;
    //商品类目的父ID
    private Long parentId;
    //商品类目名字
    private String name;
    //类目的状态 1:正常 0:删除
    private Integer status;
    //顺序编号
    private Integer sortOrder;
    //是否是父类目
    private Boolean isParent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean parent) {
        isParent = parent;
    }

}
