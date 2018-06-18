package com.nju.bff_service.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class HttpUtil {
    public static HttpResponse httpGet(String url){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        System.out.println("execute request: "+ httpGet.getURI());
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HttpResponse httpPost(String postData, String url){
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost post = new HttpPost(url);
        // 构造消息头
        post.setHeader("Content-type", "application/json; charset=utf-8");

        // 构建消息实体
        StringEntity entity = new StringEntity(postData, Charset.forName("UTF-8"));
        entity.setContentEncoding("UTF-8");
        // 发送Json格式的数据请求
        entity.setContentType("application/json");
        post.setEntity(entity);

        try {
            HttpResponse response = httpClient.execute(post);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
