/**
 * 
 */
package io.clab.mpf.shop.consumer.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;

/**
 * @author iceray
 *
 */
@Data
@TableName("t_di_uuser")
public class DiUser {

	private Long diId; //数据项ID
	
	private Integer payPoints;
	
	private Long merchantsId; //商家ID
	
	private Long uuserId; //上传用户ID
	
	
}
