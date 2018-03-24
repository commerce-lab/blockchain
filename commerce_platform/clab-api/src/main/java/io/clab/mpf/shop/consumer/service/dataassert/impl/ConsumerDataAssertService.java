/**
 * 
 */
package io.clab.mpf.shop.consumer.service.dataassert.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;

import io.clab.mpf.shop.consumer.constant.CreditChannelEnum;
import io.clab.mpf.shop.consumer.constant.CreditHisRangeEnum;
import io.clab.mpf.shop.consumer.entity.credit.ConsumerCredit;
import io.clab.mpf.shop.consumer.entity.credit.ConsumerCreditDetail;
import io.clab.mpf.shop.consumer.entity.credit.DataAssertDetail;
import io.clab.mpf.shop.consumer.entity.credit.DataAssertLabel;
import io.clab.mpf.shop.consumer.repository.credit.ConsumerCreditDetailMapper;
import io.clab.mpf.shop.consumer.repository.credit.ConsumerCreditMapper;
import io.clab.mpf.shop.consumer.repository.credit.DataAssertMapper;
import io.clab.mpf.shop.consumer.repository.user.UserMapper;
import io.clab.mpf.shop.consumer.service.dataassert.IConsumerDataAssertService;
import io.clab.mpf.shop.consumer.service.tag.ITagService;
import io.clab.mpf.shop.consumer.vto.dataAssert.CreditHisByLabelVto;

/**
 * @author iceray
 * 数据资产
 */
@Service
public class ConsumerDataAssertService implements IConsumerDataAssertService {

	
	@Autowired
	private ConsumerCreditDetailMapper creditDetailMapper;
	
	@Autowired
	private ConsumerCreditMapper creditMapper;
	
	@Autowired
	private ITagService tagService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private DataAssertMapper dataAssertMapper;
	
	/**
	 * 关联商家总数
	 */
	@Override
	public int getRelevantBusinessCount(Long consumerId) {
		return creditDetailMapper.relevantBusinessCount(consumerId);
	}

	/**
	 * 获取总的数据资产
	 */
	@Override
	public long getGeneralIncome(Long consumerId) {
		ConsumerCredit credit = new ConsumerCredit();
		credit.setUserId(consumerId);
		credit = creditMapper.selectOne(credit);
		if(credit == null)
			return 0L;
		return credit.getAssertValue();
	}

	
	@Override
	public List<DataAssertLabel> getDataAssertLabel(Long consumerId) {
		return this.creditDetailMapper.findAssertLableStatics(consumerId, CreditHisRangeEnum.ADD.getType());
	}

	/**
	 * 数据资产流水
	 */
	@Override
	public Page<DataAssertDetail> getDataAssertHis(Long consumerId,
			int pageNow, int pageSize) {
		Long userId = findUserIdByConsumerId(consumerId);
		if(userId == 0) {
			return new Page<DataAssertDetail>(pageNow,pageSize == 0?10:pageSize);
		}
		
		Page<DataAssertDetail> page = new Page<DataAssertDetail>(pageNow,pageSize == 0?10:pageSize);
		page.setRecords(dataAssertMapper.getDataAssertList(page, userId));
		
		return page;
	}

	/**
	 * 数据授权操作
	 */
/*	@Override
	@Transactional
	public void grantDataItem(Long dataItemId,Long consumerId) {
		
		//查询未授权用户数据
		DiUser diUser = diUserMapper.findNotAutUser(dataItemId);
		if(diUser == null) {
			throw new ClabServerException("未找到该用户信息或者该用户已授权");
		}
		
		//更新授权状态
		DiUploadUser uploadUser = new DiUploadUser();
		uploadUser.setUuserId(diUser.getUuserId());
		uploadUserMapper.updateById(uploadUser);
		
		//新增积分流水
		ConsumerCreditDetail creditDetail = new ConsumerCreditDetail();
		creditDetail.setCreateTime(new Date());
		creditDetail.setQuantity(diUser.getPayPoints());
		creditDetail.setSource(CreditChannelEnum.SHOP_DATA_GRANT.getSource());
		creditDetail.setType(CreditHisRangeEnum.ADD.getType());
		creditDetail.setExt(diUser.getMerchantsId().toString()); //增加商家关联
		creditDetail.setUserId(consumerId);
		creditDetailMapper.insert(creditDetail);
		
		//更新积分汇总数据
		ConsumerCredit credit = new ConsumerCredit();
		credit.setUserId(consumerId);
		credit.setAssertValue(diUser.getPayPoints());
		creditMapper.updateCreditAssert(credit);
		
	}
*/
	@Override
	public boolean isGrantLabel(Long consumerId, String label) {
		Long userId = findUserIdByConsumerId(consumerId);
		if(userId == 0)
			return false;
		
		Integer tagId = tagService.getTagByCode(label);
		return tagService.isGrantLabel(consumerId, tagId);
	}
	

	@Override
	public DataAssertDetail getDataDetail(Long consumerId,Long dataId) {
		
		DataAssertDetail detail = this.dataAssertMapper.getDataAssertDetail(dataId);
		if(detail == null)
			return new DataAssertDetail();
		Long userId = findUserIdByConsumerId(consumerId);
		if(userId == 0)
			return detail;
		
		detail.setLabelList(tagService.findTagNameByUserId(userId));
		return detail;
	}
	@Override
	public long getCreditQuantityByLabel(Long consumerId, String label) {
		Integer tagId = tagService.getTagByCode(label);
		Long quantity = creditDetailMapper.tagAssertCount(consumerId, CreditHisRangeEnum.ADD.getType(), tagId);
		return quantity == null ? 0 : quantity;
	}

	@Override
	public List<CreditHisByLabelVto> getCreditHisByLabel(Long consumerId, String label) {
		Integer tagId = tagService.getTagByCode(label);
		List<ConsumerCreditDetail> creditdetails = creditDetailMapper.tagAssertStatics(CreditHisRangeEnum.ADD.getType(), consumerId,tagId);
		return transferToVto(creditdetails);
	}
	
	private List<CreditHisByLabelVto> transferToVto(List<ConsumerCreditDetail> creditdetails) {
		
		if(creditdetails == null || creditdetails.isEmpty()) {
			return new ArrayList<CreditHisByLabelVto>();
		}
		
		return creditdetails.stream().map(creditdetail -> {
			CreditHisByLabelVto detailVto = new CreditHisByLabelVto();
			BeanUtils.copyProperties(creditdetail, detailVto);
			detailVto.setTitle(CreditChannelEnum.getValueBySource(creditdetail.getSource()).getName());
			return detailVto;
		}).collect(Collectors.toList());
		
	}

	@Override
	public boolean grantLabelSwitch(Long consumerId, String label, boolean isGrantg) {
		Long userId = findUserIdByConsumerId(consumerId);
		if(userId == 0)
			return false;
		Integer tagId = tagService.getTagByCode(label);
		return tagService.grantLabelSwitch(userId, tagId, isGrantg);
	}
	
	/**
	 * 根据消费者ID获取上传用户数据的用户ID
	 * @param consumerId
	 * @return
	 */
	private Long findUserIdByConsumerId(Long consumerId) {
		Long userId = userMapper.selectUserIdByConsumerId(consumerId);
		return userId == null ? 0L : userId;
	}

}
