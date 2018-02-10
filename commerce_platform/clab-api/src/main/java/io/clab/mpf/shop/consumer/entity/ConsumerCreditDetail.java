/**
 * 
 */
package io.clab.mpf.shop.consumer.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;

/**
 * @author iceray
 *
 */
@Data
@TableName("t_user_credit_detail")
public class ConsumerCreditDetail {

	private Long id;
	
	private Integer source; //1 -- 数据授权 2 -- 商家赠送 3 -- 消费返还 4 -- 日常任务
	
	private Long sourceSum; //每一种来源消费总数
	
	private Long userId; //用户ID
	
	private Integer quantity; //积分
	
	private Date createTime; //变更时间
	
	private Integer type; //积分变更类型  1 -- 消费；2 -- 增加
	
	private String ext;
}
