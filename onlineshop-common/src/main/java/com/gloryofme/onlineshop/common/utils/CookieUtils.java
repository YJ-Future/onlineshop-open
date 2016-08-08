package com.gloryofme.onlineshop.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**Cookie 工具类
 * Created by YU on 2016/6/15.
 */
public class CookieUtils {

    /**
     * 获取cookie值 不适用UTF-8编码
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName){
        return getCookieValue(request,cookieName,false);
    }

    /**
     * 获取cookie值
     * @param request
     * @param cookieName cookie名字
     * @param isEncode UTF-8编码
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isEncode){
        Cookie[] cookies = request.getCookies();
        if(cookies == null || StringUtils.isEmpty(cookieName))
            return null;
        String value = null;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(cookieName)){
                try {
                    value = isEncode ? URLDecoder.decode(cookie.getValue(),"UTF-8") : cookie.getValue();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return value;
    }

    /**
     *获取Cookie值
     * @param request
     * @param cookieName cookie名字
     * @param encode 编码格式
     * @return
     */
    public static String getCookieValue(HttpServletRequest request,String cookieName,String encode){
        Cookie[] cookies = request.getCookies();
        if(cookies == null || StringUtils.isEmpty(cookieName) || StringUtils.isEmpty(encode))
            return null;
        String value = null;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(cookieName)){
                try {
                    value = URLDecoder.decode(cookie.getValue(),encode);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return value;
    }
}
