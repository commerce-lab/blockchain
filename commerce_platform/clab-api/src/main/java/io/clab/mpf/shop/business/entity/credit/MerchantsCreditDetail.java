/**
 * 
 */
package io.clab.mpf.shop.business.entity.credit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;

/**
 * @author iceray
 *
 */
@Data
@TableName("t_merchants_credit_detail")
@ApiModel(value="商家积分明细")
public class MerchantsCreditDetail {

	@ApiModelProperty("自增长")
	private Long id;

	@ApiModelProperty("收入总数")
	private Long quantityAddSum; //
	
	@ApiModelProperty("支出总数")
	private Long quantiityReduceSum; //
	
	@ApiModelProperty("商家ID")
	private Long merchantId; //
	
	@ApiModelProperty("统计月份")
	private String month; //
	
	@ApiModelProperty("积分变更类型  1 -- 消费；2 -- 增加")
	private Integer type; //
	
}
