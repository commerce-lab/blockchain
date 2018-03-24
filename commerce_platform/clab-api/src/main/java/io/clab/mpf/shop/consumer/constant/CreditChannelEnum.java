package io.clab.mpf.shop.consumer.constant;
/***
 * 积分来源渠道
 * **/
public enum CreditChannelEnum {
	SHOP_DATA_GRANT(1,"数据授权"),SHOP_PRESENTED(2,"商家赠送"),RETURN(3,"消费返还"),DAILYTASK(4,"日常任务");
	
	private Integer source;
	
	private String value;
	
	private CreditChannelEnum(Integer source,String value){
		this.source = source;
		this.value = value;
	}
	
	public Integer getSource() {
		return this.source;
	}
	
	public String getName() {
		return this.value;
	}
	
	public static CreditChannelEnum getValueBySource(Integer source) {
		if(source == 1)
			return SHOP_DATA_GRANT;
		if(source == 2)
			return SHOP_PRESENTED;
		if(source == 3)
			return RETURN;
	    return DAILYTASK;
	}
}
