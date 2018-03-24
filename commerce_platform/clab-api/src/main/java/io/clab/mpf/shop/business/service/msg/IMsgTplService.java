package io.clab.mpf.shop.business.service.msg;

import com.baomidou.mybatisplus.plugins.Page;

import io.clab.mpf.shop.business.entity.msg.MsgTpl;
/**
 * 消息模板
 * **/
public interface IMsgTplService {
	public Page<MsgTpl> getList(long creator,int pageNow,int pageSize);
}
