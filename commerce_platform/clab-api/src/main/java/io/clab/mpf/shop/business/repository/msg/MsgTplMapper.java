package io.clab.mpf.shop.business.repository.msg;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import io.clab.mpf.shop.business.entity.msg.MsgTpl;

public interface MsgTplMapper  extends BaseMapper<MsgTpl>{
	List<MsgTpl> getList();
}
