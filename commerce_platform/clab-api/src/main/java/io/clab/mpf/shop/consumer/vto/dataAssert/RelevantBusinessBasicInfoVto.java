package io.clab.mpf.shop.consumer.vto.dataAssert;

import lombok.Data;

@Data
public class RelevantBusinessBasicInfoVto {
	private int businessCount;//相关商家数量
	private Long income;//从商家从获得的积分总收入
}
