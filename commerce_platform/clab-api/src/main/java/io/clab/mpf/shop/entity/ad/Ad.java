package io.clab.mpf.shop.entity.ad;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 广告实体类
 *
 */
public class Ad {
	private Integer adId;
	private Integer adPositionId;
	private String image;
	private String description;
	private String url;
	private Byte state;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;
	private Byte delState;

	public Integer getAdId() {
		return adId;
	}

	public void setAdId(Integer adId) {
		this.adId = adId;
	}

	public Integer getAdPositionId() {
		return adPositionId;
	}

	public void setAdPositionId(Integer adPositionId) {
		this.adPositionId = adPositionId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
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

}
