package io.clab.mpf.shop.entity.article;

import java.io.Serializable;
import java.util.Date;

/**
 */
public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer articleId;
	private Integer categoryId;
	private String title;
	private String content;
	private String author;
	private Date publishTime;
	private String publisher;
	private Byte isPublished;
	private Byte isTop;
	private Date createTime;
	private Date updateTime;
	private Byte state;
	private Byte delState;
	private Integer adminId;
	private String keyWords;
	private String imageUrl;
	private Byte recommend;

	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", categoryId=" + categoryId
				+ ", title=" + title + ", content=" + content + ", author="
				+ author + ", publishTime=" + publishTime + ", publisher="
				+ publisher + ", isPublished=" + isPublished + ", isTop="
				+ isTop + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", state=" + state + ", delState=" + delState
				+ ", adminId=" + adminId + ", keyWords=" + keyWords
				+ ", imageUrl=" + imageUrl + ", recommend=" + recommend + "]";
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Byte getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(Byte isPublished) {
		this.isPublished = isPublished;
	}

	public Byte getIsTop() {
		return isTop;
	}

	public void setIsTop(Byte isTop) {
		this.isTop = isTop;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Byte getRecommend() {
		return recommend;
	}

	public void setRecommend(Byte recommend) {
		this.recommend = recommend;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	public Byte getDelState() {
		return delState;
	}

	public void setDelState(Byte delState) {
		this.delState = delState;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

}
