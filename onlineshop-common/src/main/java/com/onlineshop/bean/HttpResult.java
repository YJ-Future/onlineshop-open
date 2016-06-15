package com.onlineshop.bean;

/**
 * Created by YU on 2016/6/15.
 */
public class HttpResult {

    //返回响应码
    private Integer code;

    //相应内容
    private String content;

    public HttpResult(){}

    public HttpResult(Integer code, String content){
        this.code = code;
        this.content = content;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
