package com.gloryofme.onlineshop.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**json工具类
 * Created by YU on 2016/6/15.
 */
public class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonUtils(){}

    /**
     * 对象转Json字符串
     * @param data 对象
     * @return json字符串
     */
    public static String objectToJson(Object data){
        try {
            String json = MAPPER.writeValueAsString(data);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json字符串转对象
     * @param json json字符串
     * @param objType 对象类型
     * @param <T>
     * @return 对象
     */
    public static <T> T jsonToObject(String json,Class<T> objType){
        try {
            T obj = MAPPER.readValue(json,objType);
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json字符串转List对象
     * @param json json字符串
     * @param objType 对象类型
     * @param <T>
     * @return List对象
     */
    public static <T> List<T> jsonToList(String json, Class<T> objType){
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class,objType);
        try {
            List<T> list = MAPPER.readValue(json,javaType);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
