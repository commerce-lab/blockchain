/**
 * 
 */
package io.clab.mpf.shop.consumer.user;

import io.clab.mpf.shop.util.UserIdWroker;

/**
 * @author iceray
 *
 */
public class IdTest {

	public static void main(String[] args) {
		
		System.out.println(UserIdWroker.getConsumerId());
		System.out.println(UserIdWroker.getConsumerId());
		System.out.println(UserIdWroker.getConsumerId());
		System.out.println(UserIdWroker.getConsumerId());
		
		System.out.println(UserIdWroker.getMerchantsId());
		System.out.println(UserIdWroker.getMerchantsId());
		System.out.println(UserIdWroker.getMerchantsId());
		System.out.println(UserIdWroker.getAdminId());
		
	}
}
