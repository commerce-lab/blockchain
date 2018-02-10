package io.clab.mpf.shop.entity.module;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 系统module实体类
 */
public class Module {
	
	@ApiModelProperty("module ID")
	private int moduleId;
	
	@ApiModelProperty("上级ID")
	private int parentId;
	
	@ApiModelProperty("module 名称")
	private String name;
	
	@ApiModelProperty("地址")
	private String url;
	
	@ApiModelProperty("描述")
	private String description;
	
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	@ApiModelProperty("更新时间")
	private Date updateTime;
	
	@ApiModelProperty("状态")
	private Byte state;

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

}
