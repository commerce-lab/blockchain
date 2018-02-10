package io.clab.mpf.shop.entity.user;

import java.util.Date;

public class UserVo { 
	private Integer userId;

	private String openid;

	private String nickname;

	private String headimgurl;

	private String sex;

	private String phone;

	private Date createTime;

	private String province;

	private String city;

	private String country;

	private Integer state;

	private Date updateTime;
	
	/**
	 * 创建时间_小于等于,即创建时间上限
	 */
	private String createTime_le;

	/**
	 * 创建时间_大于等于，即创建时间下限
	 */
	private String createTime_ge;

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid == null ? null : openid.trim();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl == null ? null : headimgurl.trim();
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country == null ? null : country.trim();
	}

	public String getCreateTime_le() {
		return createTime_le;
	}

	public void setCreateTime_le(String createTime_le) {
		this.createTime_le = createTime_le;
	}

	public String getCreateTime_ge() {
		return createTime_ge;
	}

	public void setCreateTime_ge(String createTime_ge) {
		this.createTime_ge = createTime_ge;
	}
}
