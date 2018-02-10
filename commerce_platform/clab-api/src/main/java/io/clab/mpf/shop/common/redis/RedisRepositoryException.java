/**
 * 
 */
package io.clab.mpf.shop.common.redis;

/**
 * @author iceray
 *
 */
public class RedisRepositoryException extends RuntimeException {

	private static final long serialVersionUID = 6411368582544966987L;

	public RedisRepositoryException(String message)
	{
		super(message);
	}
	
	public RedisRepositoryException(String message,Throwable throwable)
	{
		super(message,throwable);
	}
}
