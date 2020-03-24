package com.andon.service;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

public interface UtilsService {

	
	
	/**
	 * HttpClient (GET)
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
    String callToGET(Map<String, Object> map) throws Exception;
    
	/**
	 * HttpClient (POST)
	 */
    Map<String, Object> callToPost(Map<String, Object> map) throws Exception;
}
