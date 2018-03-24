/**
 * 
 */
package io.clab.mpf.shop.util;


/**
 * @author iceray
 *
 */
public class UserIdWroker {

	private static final MpfSequence worker = new MpfSequence(4,3,11);
	
	private static final long comsumerIdPre =  1000000000000000000l;
	
	private static final long merchantsIdPre =  1100000000000000000l;
	
	private static final long adminIdPre =  1200000000000000000l;
	
	public static long getConsumerId() {
        return comsumerIdPre + worker.nextId();
    }
	
	public static long getMerchantsId() {
		return merchantsIdPre + worker.nextId();
	}
	
	public static long getAdminId() {
		return adminIdPre + worker.nextId();
	}
	
}
