package com.gloryofme.onlineshop.common.bean;

/**
 * 文件上传返回结果
 * Created by YU on 2016/6/30.
 */
public class PicUploadResult {
    //错误信息
    private Integer error;
    //返回图片服务器上地址
    private String url;
    //图片文件的宽度
    private String width;
    //图片文件的高度
    private String height;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
