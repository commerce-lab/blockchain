package io.clab.mpf.shop.common.constant;

public enum MsgStateEnum {
	UNREAD(1,"未读"),READ(2,"已读"),DELETED(3,"已删除");
	private Integer state;
	private String txt;
	private MsgStateEnum(Integer state,String txt) {
		this.state = state;
		this.txt = txt;
	}
	public Integer getState() {
		return state;
	}
	public String getTxt() {
		return txt;
	}
	
}
