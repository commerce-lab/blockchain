package io.clab.mpf.shop.entity.module;

import java.util.Date;

/**
 * 系统module实体类
 *
 */
public class ModuleVo {
	private int moduleId;
	private int parentId;
	private String name;
	private String url;
	private String description;
	private Date createTime;
	private Date updateTime;
	private Byte state;
	private Byte isOwnered; // 是否拥有 1 拥有 2 不拥有

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	public Byte getIsOwnered() {
		return isOwnered;
	}

	public void setIsOwnered(Byte isOwnered) {
		this.isOwnered = isOwnered;
	}

}
