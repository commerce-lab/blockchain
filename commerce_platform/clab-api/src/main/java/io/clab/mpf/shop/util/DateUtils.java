package io.clab.mpf.shop.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DateUtils {

	public static SimpleDateFormat getSDF(String format) {
		return new SimpleDateFormat(format);
	}
	public static Date parseDate(String dateStr,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String getFormatDate(Date date,String format) {
		SimpleDateFormat sdf = getSDF(format);
		String formatDateStr = sdf.format(date);
		return formatDateStr;
	}
	public static String getCreateTime() {
		return getFormatDate(new Date(), "yyyy-MM-dd hh:mm:ss");
	}
	
	public static Date getBeginTimeByYear(String year) {
		String beginYearTime = year + "-01-01 00:00:00";
		SimpleDateFormat sdf = getSDF("yyyy-MM-dd hh:mm:ss");
		try {			
			return sdf.parse(beginYearTime);
		} catch(Exception e) {
			return null;
		}
	}
}
