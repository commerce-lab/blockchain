package util;

public class StringUtil {

	public static Boolean isNotNull(String str){
		if(str != null && !"".equals(str)){
			return true;
		}
		return false;
	}
}
