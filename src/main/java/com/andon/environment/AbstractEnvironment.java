package com.andon.environment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public abstract class AbstractEnvironment implements Environment {
	public static final String TEST = "test";
	public static final String PRODUCT = "product";
	public static final String PREPRODUCTION = "preproduction";
	public static final String DEVELOP = "develop";

	private static final String ENVIRONMENT_FILE = "/environment.properties";
	private static final String ENVIRONMENT;
	

	static {
		Properties props = new Properties();
		InputStream in = AbstractEnvironment.class.getResourceAsStream(ENVIRONMENT_FILE);
		try {
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		ENVIRONMENT=props.getProperty("environment",TEST);
	}

	@Override
	public Object handle(Object obj) {
		switch (ENVIRONMENT) {
		case TEST:
			return handleForTest(obj);
		case PRODUCT:
			return handleForProduct(obj);
		case PREPRODUCTION:
			return handleForPreproduction(obj);
		case DEVELOP:
			return handleForDevelop(obj);
		default:
			return handleForDefault(obj);
		}
	}

}
