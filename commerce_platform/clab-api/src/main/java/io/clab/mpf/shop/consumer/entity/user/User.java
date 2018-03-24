/**
 * 
 */
package io.clab.mpf.shop.consumer.entity.user;

import java.util.Date;

import lombok.Data;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author iceray
 *
 */
@Data
@TableName("t_user")
@ApiModel(value="用户信息")
public class User {

	@TableId(type=IdType.INPUT)
	private Long id;
	@ApiModelProperty("自增长")
	private String mobile;
	
	private String pwd;
	@ApiModelProperty("昵称")
	private String nickname;
/*	
	private Integer province;//省编码
	
	private Integer city;//市编码
*/	
	@ApiModelProperty("出生日期yyyyMMdd")
	private String birthday;
	
	@ApiModelProperty("性别")
	private String sex;
	
	private String salt;
	
	private Integer state;
	
	private Date createTime;
}
