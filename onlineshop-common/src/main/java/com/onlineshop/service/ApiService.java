package com.onlineshop.service;

import com.onlineshop.bean.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by YU on 2016/6/21.
 */
public class ApiService {

    @Autowired(required = false)
    private CloseableHttpClient httpClient;

    @Autowired(required = false)
    private RequestConfig requestConfig;

    /**
     * HTTP GET 请求
     * @param url
     * @return
     */
    public String doGet(String url) {
        return this.doGet(url);
    }

    /**
     *HTTP GET 请求
     * @param url   请求地址
     * @param params key-value 请求参数
     * @return  相应内容
     * @throws URISyntaxException
     * @throws IOException
     */
    public String doGet(String url, Map<String, Object> params) throws URISyntaxException, IOException {
        URI uri = null;
        //拼接url参数
        if (params != null) {
            URIBuilder builder = new URIBuilder(url);
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                builder.addParameter(entry.getKey(), String.valueOf(entry.getValue()));
            }
            uri = builder.build();
        }

        //创建HTTP GET 请求
        HttpGet httpGet = null;
        if (uri == null) {
            httpGet = new HttpGet(url);
        } else {
            httpGet = new HttpGet(uri);
        }

        httpGet.setConfig(this.requestConfig);

        //HTTP请求 返回结果
        CloseableHttpResponse response = null;

        if (httpClient != null) {
            //执行请求
            try {
                response = httpClient.execute(httpGet);
                if (response.getStatusLine().getStatusCode() == 200) {
                    return EntityUtils.toString(response.getEntity(), "UTF-8");
                }
            } finally {
                if (response != null)
                    response.close();
            }
        }
        return null;
    }

    /**
     * HTTP POST 请求
     * @param url 请求地址
     * @param params key-value 请求参数
     * @return
     * @throws IOException
     */
    public HttpResult doPost(String url, Map<String, Object> params) throws IOException {

        //创建 HTTP POST 请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(this.requestConfig);

        //构造POST 请求实体
        if(params != null){
            List<NameValuePair> parameters = new ArrayList<>();
            for(Map.Entry<String, Object> entry : params.entrySet()){
                parameters.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
            }
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(parameters,"UTF-8");
            httpPost.setEntity(urlEncodedFormEntity);
        }

        //执行请求 返回结果
        CloseableHttpResponse response = null;
        try{
            response = httpClient.execute(httpPost);
            HttpResult httpResult = new HttpResult();
            httpResult.setCode(response.getStatusLine().getStatusCode());
            //判断返回状态
            if(response.getStatusLine().getStatusCode() == 200){
                httpResult.setContent(EntityUtils.toString(response.getEntity(),"UTF-8"));
            }
            return httpResult;
        }finally {
            if(response != null)
                response.close();
        }
    }

    /**
     *POST 请求 json 参数
     * @param url 请求地址
     * @param json 请求json参数
     * @return  请求返回结果
     * @throws IOException
     */
    public HttpResult doJsonPost(String url, String json) throws IOException {
        //创建 HTTP POST 请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(this.requestConfig);
        if(!StringUtils.isEmpty(json) && !StringUtils.isBlank(json)){
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
        }

        //执行请求 返回结果
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpPost);
            HttpResult httpResult = new HttpResult();
            httpResult.setCode(response.getStatusLine().getStatusCode());
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                httpResult.setContent(EntityUtils.toString(response.getEntity(), "UTF-8"));
            }
            return httpResult;
        } finally {
            if (response != null)
                response.close();
        }
    }
}
