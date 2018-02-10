package io.clab.mpf.shop.business.entity.uuser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;


@ApiModel(value="商家上传用户标签")
public class UuserTag implements Serializable{

	private Uuser uuser = new Uuser();
	
	private List<Tag> tags = new ArrayList<Tag>();

	public Uuser getUuser() {
		return uuser;
	}

	public void setUuser(Uuser uuser) {
		this.uuser = uuser;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
}
