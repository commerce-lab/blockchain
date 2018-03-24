package io.clab.mpf.shop.business.service.msg.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.clab.mpf.shop.business.entity.msg.MsgTplSubj;
import io.clab.mpf.shop.business.repository.msg.MsgTplSubjMapper;
import io.clab.mpf.shop.business.service.msg.IMsgTplSubjService;
@Service
public class MsgTplSubjService extends ServiceImpl<MsgTplSubjMapper,MsgTplSubj>  implements IMsgTplSubjService {

	@Override
	public boolean hasContainName(String name, Long businessId) {
		MsgTplSubj msgTplSubj = new MsgTplSubj();
		msgTplSubj.setCreator(businessId);
		msgTplSubj.setName(name);
		msgTplSubj.setDeleted(0);
		Wrapper<MsgTplSubj> wrapper = new EntityWrapper<>(msgTplSubj);
		int count = this.selectCount(wrapper );
		return count > 0 ;
	}




}
