package com.onlineshop.spring.converter;

import com.fasterxml.jackson.core.JsonEncoding;
import com.sun.xml.internal.ws.client.RequestContext;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 继承MappingJackson2HttpMessageConverter 支持jsonp跨域访问
 * Created by YU on 2016/7/2.
 */
public class CallbackMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter{

    private String callbackName;

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        String callbackParam = request.getParameter(callbackName);
        if(StringUtils.isEmpty(callbackParam)){
            super.writeInternal(object, outputMessage);
        }else{
            JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
            String result = callbackParam + "(" + super.getObjectMapper().writeValueAsString(object) +");";
            IOUtils.write(result, outputMessage.getBody(), encoding.getJavaName());
        }
    }

    public String getCallbackName() {
        return callbackName;
    }

    public void setCallbackName(String callbackName) {
        this.callbackName = callbackName;
    }
}
