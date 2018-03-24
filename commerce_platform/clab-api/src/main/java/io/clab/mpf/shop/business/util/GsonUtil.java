package io.clab.mpf.shop.business.util;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Gson的工具类
 * 
 * @author cenxi
 * 
 */
public class GsonUtil {

	private static Gson gson = new GsonBuilder()
			.registerTypeAdapter(Date.class, new UtilDateSerializer())
			.registerTypeAdapter(Calendar.class, new UtilCalendarSerializer())
			.registerTypeAdapter(GregorianCalendar.class,
					new UtilCalendarSerializer())
			.setDateFormat(DateFormat.LONG)
			.setPrettyPrinting()
			.serializeNulls()// 如果字段值为null,也照样输出
			.disableHtmlEscaping()  //防止html被转义
			.create();

	/**
	 * @param bean
	 * @return 返回JSON字符串
	 */
	public static String bean2json(Object bean) {
		//String str = gson.toJson(bean);
		//str = str.replaceAll("\r|\n", "");
		//str = str.replaceAll("\\\\", "");
		return gson.toJson(bean);
	}

	/**
	 * @param json
	 * @param type
	 * @return  返回对象
	 */
	public static <T> T json2bean(String json, Type type) {
		return gson.fromJson(json, type);
	}

	private static class UtilDateSerializer implements JsonSerializer<Date>,
			JsonDeserializer<Date> {
		@Override
		public JsonElement serialize(Date date, Type type,
				JsonSerializationContext context) {
			return new JsonPrimitive(date.getTime());
		}

		@Override
		public Date deserialize(JsonElement element, Type type,
				JsonDeserializationContext context) throws JsonParseException {
			return new Date(element.getAsJsonPrimitive().getAsLong());
		}
	}

	private static class UtilCalendarSerializer implements
			JsonSerializer<Calendar>, JsonDeserializer<Calendar> {
		@Override
		public JsonElement serialize(Calendar cal, Type type,
				JsonSerializationContext context) {
			return new JsonPrimitive(Long.valueOf(cal.getTimeInMillis()));
		}

		@Override
		public Calendar deserialize(JsonElement element, Type type,
				JsonDeserializationContext context) throws JsonParseException {
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(element.getAsJsonPrimitive().getAsLong());
			return cal;
		}

	}

}
