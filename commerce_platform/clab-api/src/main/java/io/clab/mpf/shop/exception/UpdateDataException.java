package io.clab.mpf.shop.exception;
/**
 * 未能够成功更新数据库中的数据
 * by czh
 * ***/
public class UpdateDataException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public String toString() {
		return "数据更新异常 ！";
	}
}
