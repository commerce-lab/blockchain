package io.clab.mpf.shop.service.serialNumber;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import io.clab.mpf.shop.dao.serialNumber.SerialNumberDao;

/**
 * 流水号生成
 */
@Service
public class SerialNumberService {
	@Resource
	private SerialNumberDao serialNumberDao;

	public String getNum() {
		Date da = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = format.format(da);
		Integer no = 1;
		String updateDate = null;
		synchronized (this) {
			Map<String, String> map = serialNumberDao.getNum();
			if (null != map) {

				String num = map.get("num");
				String date = map.get("date");
				if (currentDate.equals(date)) {
					// 直接增加流水号
					no = Integer.valueOf(num);
					no++;
				} else {
					updateDate = currentDate;
					no = 1;
				}
				serialNumberDao.update(updateDate, no + "");
			} else {
				// 如果数据库没有记录，则新增一条记录
				serialNumberDao.insert(currentDate, no + "");
			}
		}
		String prex = "15";
		for (int i = 0; i < 5 - (String.valueOf(no).length()); i++) {
			prex = prex + "0";
		}

		return currentDate.replace("-", "") + prex + no;
	}
}
