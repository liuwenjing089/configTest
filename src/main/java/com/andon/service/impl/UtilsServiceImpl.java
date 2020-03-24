package com.andon.service.impl;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.andon.bean.UserCallInterface;
import com.andon.service.UtilsService;

import net.sf.json.JSONObject;

@SuppressWarnings("deprecation")
@Service
@Transactional
public class UtilsServiceImpl implements UtilsService {
	@Autowired

	private static final String CHARSET = "UTF-8";


	@SuppressWarnings("resource")
	@Override
	public String callToGET(Map<String, Object> map) throws Exception{

        HttpClient httpClient = new DefaultHttpClient();
        
        // 连接超时
        httpClient.getParams().setParameter(
                    CoreConnectionPNames. CONNECTION_TIMEOUT, 5000);
         // 读取超时
        httpClient.getParams().setParameter(
                    CoreConnectionPNames. SO_TIMEOUT, 5000);

        HttpGet httpGet = new HttpGet(map.get("url").toString());
        
        if(!StringUtils.isBlank(map.get("token").toString())){
            httpGet.setHeader("Authorization", "JWT "+ map.get("token").toString());    
        }
   
        String entityStr = null;
        try {
	        HttpResponse httpResponse = httpClient.execute(httpGet);
	        HttpEntity entity = httpResponse.getEntity();
	        StatusLine statusLine = httpResponse.getStatusLine();
	        int statusCode = statusLine.getStatusCode();
            if(statusCode == 200){
    	        entityStr = EntityUtils.toString(entity, CHARSET);

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

		return entityStr;
	}

	@SuppressWarnings("resource")
	@Override
	public Map<String, Object> callToPost(Map<String, Object> map) throws Exception{
		
		Map<String, Object> resutlMap = new HashMap<String, Object>();

        String entityStr = null;
        
        String str = JSONObject.fromObject((UserCallInterface)map.get("user")).toString();
        
        HttpClient httpClient = new DefaultHttpClient();
        // 连接超时
        httpClient.getParams().setParameter(
                    CoreConnectionPNames. CONNECTION_TIMEOUT, 5000);
        // 读取超时
        httpClient.getParams().setParameter(
                    CoreConnectionPNames. SO_TIMEOUT, 5000);
        
        HttpPost httpPost = new HttpPost(map.get("url").toString());
        httpPost.setEntity(new StringEntity(str, Charset.forName("utf-8")));
        httpPost.setHeader("Content-Type","application/json");

        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
	        StatusLine statusLine = httpResponse.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if(statusCode == 200){
    	        entityStr = EntityUtils.toString(entity, CHARSET);
    	        resutlMap.put("code", statusCode);
    	        resutlMap.put("value", entityStr);
            }else{
    	        resutlMap.put("code", statusCode);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
        	resutlMap.put("code", "0");
            e.printStackTrace();
        } 
 
        return resutlMap;

	}





}
