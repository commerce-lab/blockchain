package io.clab.mpf.shop.export;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class DataExporterImpl  {
	private static Logger logger = Logger.getLogger(DataExporterImpl.class);
	private static String exportRootPath = "exportdir";

	public DataExporterImpl() {
		File exportRoot = new File(exportRootPath);
		if (!(exportRoot.exists())) {
			exportRoot.mkdirs();
		}
		logger.info("用于临时存放导出文件的路径为:" + exportRoot.getAbsolutePath());
	}

	public void export(ExportEntity entity,HttpServletResponse response) throws Exception {
		File exportRoot = new File(exportRootPath);
		String uuid = UUID.randomUUID().toString();
		File folderPath = new File(exportRoot, uuid);
		folderPath.mkdirs();
		File excelFile = new File(folderPath, entity.getFileName() + ".xls");
		try {
			createExcel(excelFile, entity);
		} catch (Exception e) {
			logger.error("创建用于导出的excel文件出现异常", e);
			throw new Exception("生产导出文件出现错误!", e);
		}
		try {
			download(excelFile,response);
		} catch (Exception e) {
			logger.error("向浏览器推送文件出现异常", e);
			throw new Exception("向浏览器推送文件出现异常!", e);
		}
	}

	@SuppressWarnings("unchecked")
	private List<String> getDataValues(ExportEntity entity, Object data)
			throws Exception {
		Set<String> proportyNames = entity.getPro_desc_map().keySet();
		@SuppressWarnings("rawtypes")
		List result = new ArrayList();
		for (String pro : proportyNames) {
			String[] pros=pro.split("\\.");
			Object value =new Object();
			Object newData=new Object();
			newData=data;
			for(int i=0;i<pros.length && newData != null;i++){
				String methodName = "get" + pros[i].substring(0, 1).toUpperCase()
						+ pros[i].substring(1);
				Method method = newData.getClass().getMethod(methodName, new Class[0]);
				value = method.invoke(newData, new Object[0]);				
				newData=value;
			}
			
			if (value == null) {
				result.add("");
			} else {
				if(value instanceof Date){
					//日期类型，格式化
					SimpleDateFormat formatter1 = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					value = formatter1.format((Date)(value));
				}
				if(pro.equals("totalAmount")){
					//金额，特殊处理，除以100
					value=((int)value/100.00);
				}
				@SuppressWarnings("rawtypes")
				Map params = (Map) entity.getParamsMap().get(pro);
				if (params != null) {
					String value_ = (String) params.get(value.toString());
					value_ = (value_ == null) ? "未知参数类型" : value_;
					result.add(value_);
				} else {
					result.add(value.toString());
				}
			}
		}
		return result;
	}

	private void createExcel(File file, ExportEntity entity) throws Exception {
		WritableWorkbook book = null;
		try {
			book = Workbook.createWorkbook(file);
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 12,
					WritableFont.BOLD);
			WritableFont font3 = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD);

			WritableCellFormat CBwcfF1 = new WritableCellFormat(font1);
			WritableCellFormat CBwcfF2 = new WritableCellFormat(font3);
			WritableCellFormat CBwcfF3 = new WritableCellFormat();

			CBwcfF1.setAlignment(Alignment.CENTRE);
			CBwcfF2.setAlignment(Alignment.CENTRE);
			CBwcfF2.setBackground(Colour.PALE_BLUE);
			CBwcfF2.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			CBwcfF2.setWrap(true);
			CBwcfF2.setVerticalAlignment(VerticalAlignment.CENTRE);
			CBwcfF3.setWrap(true);
			CBwcfF3.setVerticalAlignment(VerticalAlignment.CENTRE);
			WritableSheet sheet = book.createSheet("sheet1", 0);

			CellView cellView = new CellView();
			cellView.setAutosize(true);

			@SuppressWarnings("rawtypes")
			Map columnWidthMap = entity.getColumnWidthMap();

			String tittle = (entity.getTitle() == null) ? entity.getFileName()
					: entity.getTitle();

			Label titleLabel = new Label(0, 0, tittle, CBwcfF1);
			sheet.addCell(titleLabel);
			Set<String> headers = entity.getPro_desc_map().keySet();
			int columnCount = headers.size();
			sheet.mergeCells(0, 0, columnCount - 1, 0);
			int headLableIndex = -1;
			for (String string : headers) {
				++headLableIndex;
				if (columnWidthMap.containsKey(string))
					sheet.setColumnView(headLableIndex,
							((Integer) columnWidthMap.get(string)).intValue());
				else {
					sheet.setColumnView(headLableIndex, cellView);
				}
				Label headLabel = new Label(headLableIndex, 1, (String) entity
						.getPro_desc_map().get(string), CBwcfF2);
				sheet.addCell(headLabel);
			}

			int dataIndex = 0;
			int i=1;//add by yucs
			for (@SuppressWarnings("rawtypes")
			Iterator localIterator2 = entity.getDataList().iterator(); localIterator2
					.hasNext();) {
				Object data = localIterator2.next();
				@SuppressWarnings("rawtypes")
				List values = getDataValues(entity, data);
				for (int ij = 0; ij < columnCount; ++ij) {
					Label valueLabel = new Label(ij, dataIndex + 2,
							(String) values.get(ij), CBwcfF3);
					sheet.addCell(valueLabel);
				}
				++dataIndex;
				if ((i - 1) * entity.getSizePerPage() + dataIndex == entity
						.getDataList().size()) {
					break;
				}
				if (dataIndex == entity.getSizePerPage()) {
					++i;
					dataIndex = 0;
					sheet = book.createSheet("sheet" + i, i - 1);
					Label titleLabel_ = new Label(0, 0, tittle, CBwcfF1);
					sheet.addCell(titleLabel_);
					sheet.mergeCells(0, 0, columnCount - 1, 0);
					headLableIndex = -1;
					for (String string : headers) {
						++headLableIndex;
						if (columnWidthMap.containsKey(string))
							sheet.setColumnView(headLableIndex,
									((Integer) columnWidthMap.get(string))
											.intValue());
						else {
							sheet.setColumnView(headLableIndex, cellView);
						}
						Label headLabel = new Label(headLableIndex, 1,
								(String) entity.getPro_desc_map().get(string),
								CBwcfF2);
						sheet.addCell(headLabel);
					}
				}
			}

			book.write();
		} catch (Exception e) {
			logger.error("创建excel文件出现错误!", e);
			throw e;
		} finally {
			try {
				if (book != null)
					book.close();
			} catch (Exception e) {
				logger.error("关闭excel操作相关的对象出现错误", e);
			}
		}
	}

	private void download(File excelFile,HttpServletResponse response) throws IOException {
		//HttpServletResponse response = ServletActionContext.getResponse();
		FileInputStream inStream = null;
		OutputStream output = null;
		try {
			inStream = new FileInputStream(excelFile.getAbsoluteFile());
			String filenamedisplay = URLEncoder.encode(excelFile.getName(),
					"UTF-8");
			response.setContentType("application/x-download");
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ filenamedisplay);
			response.setContentLength((int) excelFile.length());
			byte[] b = new byte[1024];

			output = response.getOutputStream();
			int len;
			while ((len = inStream.read(b)) != -1) {
				//int len;
				output.write(b, 0, len);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (inStream != null)
					inStream.close();
			} catch (IOException localIOException1) {
			}
			if (output != null) {
				output.close();
			}

			if ((excelFile != null) && (excelFile.exists())) {
				excelFile.delete();
				excelFile.getParentFile().delete();
			}
		}
	}
}
