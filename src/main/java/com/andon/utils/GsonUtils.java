package com.andon.utils;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtils {

	/**
     * String ->> object
     * @param json
     * @param classType
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String json,Type classType) {
        Gson gson = getGson();
        @SuppressWarnings("unchecked")
		T res = (T) gson.fromJson(json, classType);
        return res;
    }
    
    public static Gson getGson() {
        return new GsonBuilder()
                .serializeNulls()
                .create();
    }
}
