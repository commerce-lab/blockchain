package io.clab.mpf.shop.consumer.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;
/**
 * 地址信息
 * by czh
 * **/
@Data
@TableName("t_address")
public class Address {
	@TableId(type=IdType.UUID)
	private String id;//自增id
	private Long userId;
	private String name;//收件人名称
	private Integer province;
	private Integer city;
	private Integer area;
	private String pcadetail;
	private String address;//详细地址
	private String zip;//邮政编码
	private String mobile;
	private Integer isdefault;//是否默认
}
