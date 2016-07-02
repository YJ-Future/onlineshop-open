package com.gloryofme.onlineshop.manage.pojo;

import java.util.Date;

/**
 * Pojo 基础类
 * Created by YU on 2016/7/1.
 */
public class BasePojo {
    //创建时间
    private Date created;
    //更新时间
    private Date updated;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
