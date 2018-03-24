package io.clab.mpf.shop.common.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import io.clab.mpf.shop.common.entity.SiteMsgReceiver;
import io.clab.mpf.shop.common.vto.SiteMsgVto;

public interface SiteMsgReceiverMapper extends BaseMapper<SiteMsgReceiver>{

	List<SiteMsgVto> getAllReceiveList(Pagination page,@Param("receiverId") Long receiverId);

	Long getNotReadCount(@Param("receiverId") Long receiverId);

	List<SiteMsgVto> getAllNotReadReceiveList(Page<SiteMsgVto> page,@Param("receiverId") Long receiverId);

}
