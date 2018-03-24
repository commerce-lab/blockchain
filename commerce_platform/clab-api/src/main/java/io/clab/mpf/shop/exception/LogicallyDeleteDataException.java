package io.clab.mpf.shop.exception;
/**
 * 逻辑删除数据失败
 * by czh
 * ***/
public class LogicallyDeleteDataException  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public String toString() {
		return "删除数据失败 ！";
	}
}
