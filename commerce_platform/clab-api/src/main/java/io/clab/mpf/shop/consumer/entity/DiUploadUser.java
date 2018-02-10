/**
 * 
 */
package io.clab.mpf.shop.consumer.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;

/**
 * @author iceray
 *
 */
@Data
@TableName("t_upload_user")
public class DiUploadUser {

	@TableId("uuser_id")
	private Long uuserId;
	
	private Short authFlag;
	
	private Long phone;
}
