package io.clab.mpf.shop.export;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportEntity {
	private int sizePerPage = 4000;
	private String fileName;
	private String title;
	@SuppressWarnings("rawtypes")
	private List<?> dataList = new ArrayList();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map<String, String> pro_desc_map = new HashMap();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String, Integer> columnWidthMap = new HashMap();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map<String, Map<String, String>> paramsMap = new HashMap();

	public List<?> getDataList() {
		return this.dataList;
	}

	public int getSizePerPage() {
		return this.sizePerPage;
	}

	public void setSizePerPage(int sizePerPage) {
		this.sizePerPage = sizePerPage;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

	public Map<String, String> getPro_desc_map() {
		return this.pro_desc_map;
	}

	public void setPro_desc_map(Map<String, String> pro_desc_map) {
		this.pro_desc_map = pro_desc_map;
	}

	public Map<String, Integer> getColumnWidthMap() {
		return this.columnWidthMap;
	}

	public void setColumnWidthMap(Map<String, Integer> columnWidthMap) {
		this.columnWidthMap = columnWidthMap;
	}

	public String getFileName() {
		return this.fileName;
	}

	public Map<String, Map<String, String>> getParamsMap() {
		return this.paramsMap;
	}

	public void setParamsMap(Map<String, Map<String, String>> paramsMap) {
		this.paramsMap = paramsMap;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
