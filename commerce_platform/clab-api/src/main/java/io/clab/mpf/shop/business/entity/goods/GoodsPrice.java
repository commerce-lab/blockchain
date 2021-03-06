package io.clab.mpf.shop.business.entity.goods;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@ApiModel(value="商品价格")
public class GoodsPrice implements Serializable {

	@ApiModelProperty("商品价格id")
    private Integer priceId;

	@ApiModelProperty("商品id")
    private Integer goodsId;

	@ApiModelProperty("计量单位")
    private String unitName;

	@ApiModelProperty("库存")
    private Integer stockNum;

	@ApiModelProperty("成交价")
    private Long strikePrice;

	@ApiModelProperty("市场价")
    private Long marketPrice;
	
	@ApiModelProperty("规格大小")
    private String specSize;

	@ApiModelProperty("规格重量")
    private String specWeight;

	@ApiModelProperty("规格颜色")
    private String specColour;

	@ApiModelProperty("状态：1.启用 2.未启用 （后台未启用页面不显示，前台显示已下架）")
    private Integer state;

	@ApiModelProperty("创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

	@ApiModelProperty("更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

	@ApiModelProperty("创建者id")
    private Integer adminId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_goods_price
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_price.price_id
     *
     * @return the value of t_goods_price.price_id
     *
     * @mbggenerated
     */
    public Integer getPriceId() {
        return priceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_price.price_id
     *
     * @param priceId the value for t_goods_price.price_id
     *
     * @mbggenerated
     */
    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_price.goods_id
     *
     * @return the value of t_goods_price.goods_id
     *
     * @mbggenerated
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_price.goods_id
     *
     * @param goodsId the value for t_goods_price.goods_id
     *
     * @mbggenerated
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_price.unit_name
     *
     * @return the value of t_goods_price.unit_name
     *
     * @mbggenerated
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_price.unit_name
     *
     * @param unitName the value for t_goods_price.unit_name
     *
     * @mbggenerated
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_price.stock_num
     *
     * @return the value of t_goods_price.stock_num
     *
     * @mbggenerated
     */
    public Integer getStockNum() {
        return stockNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_price.stock_num
     *
     * @param stockNum the value for t_goods_price.stock_num
     *
     * @mbggenerated
     */
    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_price.strike_price
     *
     * @return the value of t_goods_price.strike_price
     *
     * @mbggenerated
     */
    public Long getStrikePrice() {
        return strikePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_price.strike_price
     *
     * @param strikePrice the value for t_goods_price.strike_price
     *
     * @mbggenerated
     */
    public void setStrikePrice(Long strikePrice) {
        this.strikePrice = strikePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_price.market_price
     *
     * @return the value of t_goods_price.market_price
     *
     * @mbggenerated
     */
    public Long getMarketPrice() {
        return marketPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_price.market_price
     *
     * @param marketPrice the value for t_goods_price.market_price
     *
     * @mbggenerated
     */
    public void setMarketPrice(Long marketPrice) {
        this.marketPrice = marketPrice;
    }



    public String getSpecSize() {
		return specSize;
	}

	public void setSpecSize(String specSize) {
		this.specSize = specSize;
	}

	public String getSpecWeight() {
		return specWeight;
	}

	public void setSpecWeight(String specWeight) {
		this.specWeight = specWeight;
	}

	public String getSpecColour() {
		return specColour;
	}

	public void setSpecColour(String specColour) {
		this.specColour = specColour;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_price.create_time
     *
     * @return the value of t_goods_price.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_price.create_time
     *
     * @param createTime the value for t_goods_price.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_price.update_time
     *
     * @return the value of t_goods_price.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_price.update_time
     *
     * @param updateTime the value for t_goods_price.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_price.admin_id
     *
     * @return the value of t_goods_price.admin_id
     *
     * @mbggenerated
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_price.admin_id
     *
     * @param adminId the value for t_goods_price.admin_id
     *
     * @mbggenerated
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_price
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        GoodsPrice other = (GoodsPrice) that;
        return (this.getPriceId() == null ? other.getPriceId() == null : this.getPriceId().equals(other.getPriceId()))
            && (this.getGoodsId() == null ? other.getGoodsId() == null : this.getGoodsId().equals(other.getGoodsId()))
            && (this.getUnitName() == null ? other.getUnitName() == null : this.getUnitName().equals(other.getUnitName()))
            && (this.getStockNum() == null ? other.getStockNum() == null : this.getStockNum().equals(other.getStockNum()))
            && (this.getStrikePrice() == null ? other.getStrikePrice() == null : this.getStrikePrice().equals(other.getStrikePrice()))
            && (this.getMarketPrice() == null ? other.getMarketPrice() == null : this.getMarketPrice().equals(other.getMarketPrice()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getAdminId() == null ? other.getAdminId() == null : this.getAdminId().equals(other.getAdminId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_price
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPriceId() == null) ? 0 : getPriceId().hashCode());
        result = prime * result + ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
        result = prime * result + ((getUnitName() == null) ? 0 : getUnitName().hashCode());
        result = prime * result + ((getStockNum() == null) ? 0 : getStockNum().hashCode());
        result = prime * result + ((getStrikePrice() == null) ? 0 : getStrikePrice().hashCode());
        result = prime * result + ((getMarketPrice() == null) ? 0 : getMarketPrice().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getAdminId() == null) ? 0 : getAdminId().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_price
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", priceId=").append(priceId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", unitName=").append(unitName);
        sb.append(", stockNum=").append(stockNum);
        sb.append(", strikePrice=").append(strikePrice);
        sb.append(", marketPrice=").append(marketPrice);
        sb.append(", state=").append(state);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", adminId=").append(adminId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}