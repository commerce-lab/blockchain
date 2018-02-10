/**
 * 
 */
package io.clab.mpf.shop.consumer.exception;

/**
 * @author iceray
 *
 */
public class ClabServerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5427763436000219245L;

	
	public ClabServerException(String msg,Throwable e) {
		super(msg,e);
	}
	
	public ClabServerException(String msg) {
		super(msg);
	}
}
