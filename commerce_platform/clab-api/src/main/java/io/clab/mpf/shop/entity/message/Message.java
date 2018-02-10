package io.clab.mpf.shop.entity.message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Message {
	private Integer messageId;

	private String messageTitle;

	private Byte push;
	private Byte pushNow;// 是否立即推送
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date pushTime;
	private Date createTime;
	private Date updateTime;

	private Byte delState;

	private Integer adminId;

	private String messageContext;
	private String messageShortContext;

	private String url;

	private Byte isAll;

	private String userIds;

	private Integer userId;
	private Integer state;// 查询映射字段
	private Integer userMessageId;// 查询映射字段

	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	public Byte getPushNow() {
		return pushNow;
	}

	public void setPushNow(Byte pushNow) {
		this.pushNow = pushNow;
	}

	public Byte getIsAll() {
		return isAll;
	}

	public void setIsAll(Byte isAll) {
		this.isAll = isAll;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getMessageShortContext() {
		return messageShortContext;
	}

	public void setMessageShortContext(String messageShortContext) {
		this.messageShortContext = messageShortContext;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getUserMessageId() {
		return userMessageId;
	}

	public void setUserMessageId(Integer userMessageId) {
		this.userMessageId = userMessageId;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle == null ? null : messageTitle.trim();
	}

	public Byte getPush() {
		return push;
	}

	public void setPush(Byte push) {
		this.push = push;
	}

	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Byte getDelState() {
		return delState;
	}

	public void setDelState(Byte delState) {
		this.delState = delState;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessageContext() {
		return messageContext;
	}

	public void setMessageContext(String messageContext) {
		this.messageContext = messageContext == null ? null : messageContext.trim();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}