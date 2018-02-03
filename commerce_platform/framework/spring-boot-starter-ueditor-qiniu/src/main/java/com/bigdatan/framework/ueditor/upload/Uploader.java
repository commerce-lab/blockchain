package com.bigdatan.framework.ueditor.upload;

import com.bigdatan.framework.ueditor.define.State;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class Uploader {
	private HttpServletRequest request = null;
	private Map<String, Object> conf = null;

	public Uploader(HttpServletRequest request, Map<String, Object> conf) {
		this.request = request;
		this.conf = conf;
	}

	public final State doExec() {
		
		State state = null;

		if ("true".equals(this.conf.get("isBase64"))) {
			state = new Base64Uploader().save(this.request,
					this.conf);
		} else {
			state = new BinaryUploader().save(this.request, this.conf);
		}

		return state;
	}
}
