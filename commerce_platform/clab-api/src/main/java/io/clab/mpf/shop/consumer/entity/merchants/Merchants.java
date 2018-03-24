package io.clab.mpf.shop.consumer.entity.merchants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="商家信息")
@Data
public class Merchants {

	@ApiModelProperty("自增长")
	private Long merchantsId;
	
	@ApiModelProperty("商家名称")
	private String merchantsName;
	
	@ApiModelProperty("商家类型（1：品牌商，2：广告商，3：零售商）")
	private Integer merchantsType;

}
