package io.clab.mpf.shop.business.service.msg;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.IService;

import io.clab.mpf.shop.business.entity.msg.MsgTplSubj;

/**
 * 消息模板主题
 * **/
@Service
public interface IMsgTplSubjService extends IService<MsgTplSubj>{
	/**
	 * 
	 * @param name 模板名称
	 * @param businessId 商家id
	 * @return
	 */
	public boolean hasContainName(String name,Long businessId);
}
