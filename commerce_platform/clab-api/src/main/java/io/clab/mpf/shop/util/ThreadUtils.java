package io.clab.mpf.shop.util;

public class ThreadUtils {
	/**
	 * @param ms 睡眠时间（毫秒）
	 */
	public static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
