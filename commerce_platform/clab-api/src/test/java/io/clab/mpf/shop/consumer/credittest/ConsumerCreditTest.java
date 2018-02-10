/**
 * 
 */
package io.clab.mpf.shop.consumer.credittest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.clab.mpf.shop.Application;
import io.clab.mpf.shop.consumer.constant.CreditHisRangeEnum;
import io.clab.mpf.shop.consumer.service.IConsumerCreditService;

/**
 * @author iceray
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@EnableAutoConfiguration
public class ConsumerCreditTest {

	@Autowired
	private IConsumerCreditService consumerCredit;
	
	//@Test
	public void testGetCreditInfo() {
		consumerCredit.getCreditInfo(2);
	}
	
	//@Test
	public void testAetAddCreditSource() {
		consumerCredit.getAddCreditSource(2);
	}
	
	//@Test
	public void testGetCreditHisList() {
		//consumerCredit.getCreditHisList(2, CreditHisRangeEnum.ALL, 0, 5);
		consumerCredit.getCreditHisList(2, CreditHisRangeEnum.ADD, 0, 5);
	}
}
