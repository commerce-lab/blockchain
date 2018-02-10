package io.clab.mpf.shop.entity.message;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 消息实体类
 *
 */
public class UserMessageVo {
	private Integer messageId;
	private String messageName;
	private String description;
	private Byte pushState;
	private Integer userId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;
	private Integer adminId;
	private Byte delState;

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public String getMessageName() {
		return messageName;
	}

	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Byte getPushState() {
		return pushState;
	}

	public void setPushState(Byte pushState) {
		this.pushState = pushState;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Byte getDelState() {
		return delState;
	}

	public void setDelState(Byte delState) {
		this.delState = delState;
	}

}
