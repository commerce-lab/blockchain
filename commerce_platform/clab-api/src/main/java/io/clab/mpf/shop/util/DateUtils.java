package io.clab.mpf.shop.util;

import java.text.SimpleDateFormat;
import java.util.Date;
public class DateUtils {
	public static String getFormatDate(Date date,String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String formatDateStr = sdf.format(date);
		return formatDateStr;
	}
	public static String getCreateTime() {
		return getFormatDate(new Date(), "yyyy-MM-dd hh:mm:ss");
	}
}
