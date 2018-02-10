package io.clab.mpf.shop.vo.common;

import io.swagger.annotations.ApiModelProperty;


public class FileUploadVO {

	@ApiModelProperty("上传文件新名称")
	private String fileName;
	
	@ApiModelProperty("上传文件保存路径")
	private String fileUrl;
	
	@ApiModelProperty("错误信息")
	private String error;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	
}
