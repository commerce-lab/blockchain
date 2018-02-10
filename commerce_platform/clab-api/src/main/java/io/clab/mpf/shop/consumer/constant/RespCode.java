/**
 * 
 */
package io.clab.mpf.shop.consumer.constant;

/**
 * @author iceray
 *
 */
public enum RespCode {
	
	SUCCESS("0","成功"),GET_TOKEN_ERROR("1","token生成失败"),VALIDATE_ERROR("2","校验失败");

	private String code;
	
	private String msg;
	
	private RespCode(String code,String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getMsg() {
		return this.msg;
	}
}
