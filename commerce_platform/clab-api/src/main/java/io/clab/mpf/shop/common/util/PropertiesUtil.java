package io.clab.mpf.shop.common.util;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.exception.NestableRuntimeException;

/**
 * 获取配置文件内容
 * cenxi 2016-01-12
 * */
public class PropertiesUtil {

	public static String getPropertiesVal(String fileName, String keyName) {
		try {
			return new PropertiesConfiguration(fileName).getString(keyName);
		} catch (NestableRuntimeException e2) {
			e2.printStackTrace();
		} catch (ConfigurationException e3) {
			e3.printStackTrace();
		}

		return "";
	}
	
	public static HashMap<String, String> getPropertiesVal(String fileName) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			PropertiesConfiguration pc = new PropertiesConfiguration(fileName);
			//Iterator 
			Iterator<?> it = pc.getKeys();
			while (it.hasNext()) {
				String key = (String) it.next();
				map.put(key, pc.getString(key));
			}
		} catch (NestableRuntimeException e2) {
			e2.printStackTrace();
		} catch (ConfigurationException e3) {
			e3.printStackTrace();
		}

		return map;
	}
}
