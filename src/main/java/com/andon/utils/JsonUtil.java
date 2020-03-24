package com.andon.utils;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 * json 简单操作的工具类
 * 
 * @author
 * 
 */
public class JsonUtil {

	private static Gson gson = new Gson();

	/**
	 * 将任意对象转换成相应的json字符串。 如果没有就返回null。
	 * 
	 * @param obj
	 * @return
	 */
	public static String objectToJson(Object obj) {
		String result = gson.toJson(obj);
		if (result != null) {
			return result.trim();
		} else {
			return null;
		}
	}

	/**
	 * 将json字符串转换成相应的对象。 如果不支持转型对象，就返回null。
	 * 
	 * @param str
	 * @return
	 */
	// public static <T> T jsonToBean(String str) {
	// Type type = new TypeToken<T>() {
	// }.getType();
	// try {
	// return gson.fromJson(str, type);
	// } catch (JsonSyntaxException e) {
	// e.printStackTrace();
	// return null;
	// }
	// }

	/**
	 * 将对象转换成json格式(并自定义日期格式)
	 * 
	 * @param ts
	 * @return
	 */
	public static String objectToJsonDateSerializer(Object ts,
			final String dateformat) {
		String jsonStr = null;
		gson = new GsonBuilder()
				.registerTypeHierarchyAdapter(Date.class,
						new JsonSerializer<Date>() {
							@Override
							public JsonElement serialize(Date src,
									Type typeOfSrc,
									JsonSerializationContext context) {
								SimpleDateFormat format = new SimpleDateFormat(
										dateformat);
								return new JsonPrimitive(format.format(src));
							}
						}).setDateFormat(dateformat).create();
		if (gson != null) {
			jsonStr = gson.toJson(ts);
		}
		return jsonStr;
	}



	// /**
	// * 将json格式转换成list对象
	// * @param jsonStr
	// * @return
	// */
	// public static List<?> jsonToList(String jsonStr){
	// List<?> objList=null;
	// if(gson!=null){
	// java.lang.reflect.Type type=new
	// com.google.gson.reflect.TypeToken<List<?>>(){}.getType();
	// objList=gson.fromJson(jsonStr, type);
	// }
	// return objList;
	// }
	/**
	 * 将json格式转换成map对象
	 * 
	 * @param json
	 * @return
	 */
	public static Map<String, String> jsonToMap(String json) {
		Type type = new TypeToken<Map<String, String>>() {
		}.getType();
		try {
			return gson.fromJson(json, type);
		} catch (JsonSyntaxException e) {
			return null;
		}
	}

	/**
	 * 将json转换成相应的对象。
	 * type = new TypeToken<Map<String, String>>() {}.getType();
	 * @param json
	 * @param type
	 * @return
	 */
	public static <T> T jsonToBean(String json, Type type) {
		return gson.fromJson(json, type);
	}

	/**
	 * 将map转换成json串。
	 * 
	 * @param map
	 * @return
	 */
	public static String mapToJson(Map<String, String> map) {
		Type type = new TypeToken<Map<String, String>>() {
		}.getType();
		return gson.toJson(map, type);
	}

	/**
	 * 将Map格式转换为json串。
	 * 
	 * @param map
	 * @return
	 */
	// public static String mapToJson(Map<String, String> map) {
	// Type type = new TypeToken<Map<String, String>>() {
	// }.getType();
	// return gson.toJson(map, type);
	// }

	// /**
	// * 将json转换成bean对象
	// * @param jsonStr
	// * @param cl
	// * @return
	// */
	// @SuppressWarnings("unchecked")
	// public static <T> T jsonToBeanDateSerializer(String jsonStr,Class<T>
	// cl,final String pattern){
	// Object obj=null;
	// gson=new GsonBuilder().registerTypeAdapter(Date.class, new
	// JsonDeserializer<Date>() {
	// public Date deserialize(JsonElement json, Type typeOfT,
	// JsonDeserializationContext context)
	// throws JsonParseException {
	// SimpleDateFormat format=new SimpleDateFormat(pattern);
	// String dateStr=json.getAsString();
	// try {
	// return format.parse(dateStr);
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	// return null;
	// }
	// }).setDateFormat(pattern).create();
	// if(gson!=null){
	// obj=gson.fromJson(jsonStr, cl);
	// }
	// return (T)obj;
	// }
	// /**
	// * 根据
	// * @param jsonStr
	// * @param key
	// * @return
	// */
	// public static Object getJsonValue(String jsonStr,String key){
	// Object rulsObj=null;
	// Map<?,?> rulsMap=jsonToMap(jsonStr);
	// if(rulsMap!=null&&rulsMap.size()>0){
	// rulsObj=rulsMap.get(key);
	// }
	// return rulsObj;
	// }
	//

}
