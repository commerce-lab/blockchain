package io.clab.mpf.shop.consumer.constant;

public enum CreditHisRangeEnum {
	ALL(0),ADD(2),REDUCE(1);
	
	private Integer type;
	
	private CreditHisRangeEnum(Integer type) {
		this.type = type;
	}
	
	public Integer getType() {
		return this.type;
	}
	 public static CreditHisRangeEnum get( Integer type) {  
	        for (CreditHisRangeEnum e : values()) {  
	            if(e.getType() == type) {  
	                return e;  
	            }  
	        }  
	        return null;  
	    }  
}
