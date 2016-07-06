package com.gloryofme.onlineshop.common.bean;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * Created by YU on 2016/6/15.
 */
public class EasyUIResult<T> {

    private Long total;//记录数

    private List<T> rows;

    public EasyUIResult(){

    }

    public EasyUIResult(Long total, List<T> rows){
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> EasyUIResult<T> josnToResult(String json,Class<T> clazz){
        try {
            JsonNode jsonNode = MAPPER.readTree(json);
            JsonNode dataObj = jsonNode.get("rows");
            JavaType javaType = MAPPER.getTypeFactory().constructCollectionType(List.class,clazz);
            List<T> list = null;
            list = MAPPER.readValue(dataObj.traverse(),javaType);
            return new EasyUIResult<T>(jsonNode.get("total").longValue(),list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
