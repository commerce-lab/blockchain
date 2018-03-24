package io.clab.mpf.shop.business.entity.credit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;

/**
 * 商家积分信息
 * balance = totalEarning - totalUse - freeze;
 * 
 * ***/
@Data
@TableName("t_merchants_credit_pool")
@ApiModel(value="商家积分信息")
public class MerchantsCredit {
	@ApiModelProperty("自增长")
	private Long id; //id
	@ApiModelProperty("商家ID")
	private Long merchantId; //
	@ApiModelProperty("余额")
	private Integer balance;//
	@ApiModelProperty("累计获得积分总数")
	private Integer totalEarning; //
	@ApiModelProperty("累计支出的积分总数")
	private Integer totalUse;//
	@ApiModelProperty("冻结数量")
	private Integer freeze;//
	
}
