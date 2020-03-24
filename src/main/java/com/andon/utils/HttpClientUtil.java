package com.andon.utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;


public class HttpClientUtil {

	/**
     * http 请求
     *
     * @param url
     * @param queryStringData
     * @param postData
     * @param classType
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T postJson(String url, Map<String, String> queryStringData, JSONObject postData, Type classType) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        String queryStr = "";
        if (queryStringData != null) {
            for (Map.Entry<String, String> entry : queryStringData.entrySet()) {
                if (verifyIsNullOrEmpty(queryStr)) {
                    queryStr = entry.getKey() + "=" + entry.getValue();
                    continue;
                }
                queryStr = "&" + entry.getKey() + "=" + entry.getValue();
            }
        }
        url = verifyIsNullOrEmpty(queryStr) ? url : url + "?" + queryStr;

        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
        // 解决中文乱码问题
        StringEntity stringEntity = new StringEntity(postData.toString(), "UTF-8");
        stringEntity.setContentEncoding("UTF-8");
        httpPost.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(httpPost);

        String responseStr = EntityUtils.toString(response.getEntity());

        if (verifyIsNullOrEmpty(responseStr)) {
            return null;
        }
        try {
            T responseEntity = GsonUtils.parseObject(responseStr, classType);
            return responseEntity;
        } catch (Exception ex) {
            return null;
        }
    }
    
    private static boolean verifyIsNullOrEmpty(String value) {
        boolean result = false;
        if (value == null || value.isEmpty()) {
            result = true;
        }
        return result;
    }
}
