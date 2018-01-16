package io.clab.mpf.shop.admin.modules.job.dao;

import org.apache.ibatis.annotations.Mapper;

import io.clab.mpf.shop.admin.modules.job.entity.ScheduleJobEntity;
import io.clab.mpf.shop.admin.modules.sys.dao.BaseDao;

import java.util.Map;

/**
 * 定时任务
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月1日 下午10:29:57
 */
@Mapper
public interface ScheduleJobDao extends BaseDao<ScheduleJobEntity> {
	
	/**
	 * 批量更新状态
	 */
	int updateBatch(Map<String, Object> map);
}
