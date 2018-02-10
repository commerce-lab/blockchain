package io.clab.mpf.shop.dao.serialNumber;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 *
 */
public interface SerialNumberDao {
	public Map<String, String> getNum();

	public Integer update(@Param("date") String date, @Param("num") String num);
	
	void insert(@Param("date") String date, @Param("num") String num);
}
