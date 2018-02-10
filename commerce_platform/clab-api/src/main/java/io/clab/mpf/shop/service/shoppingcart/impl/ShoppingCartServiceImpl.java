package io.clab.mpf.shop.service.shoppingcart.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import util.PageResult;

import com.github.pagehelper.PageHelper;

import io.clab.mpf.shop.dao.base.IBaseDao;
import io.clab.mpf.shop.dao.shoppingcart.ShoppingCartMapper;
import io.clab.mpf.shop.dao.user.UserPrivilegeMapper;
import io.clab.mpf.shop.entity.goodsprice.GoodsPrice;
import io.clab.mpf.shop.entity.shoppingcart.ShoppingCart;
import io.clab.mpf.shop.entity.shoppingcart.ShoppingCartDetailVO;
import io.clab.mpf.shop.entity.shoppingcart.ShoppingCartItem;
import io.clab.mpf.shop.entity.shoppingcart.ShoppingCartVo;
import io.clab.mpf.shop.entity.user.User;
import io.clab.mpf.shop.entity.user.UserPrivilege;
import io.clab.mpf.shop.service.base.impl.BaseServiceImpl;
import io.clab.mpf.shop.service.shoppingcart.IShoppingCartService;

@Service
public class ShoppingCartServiceImpl extends BaseServiceImpl<ShoppingCart>
		implements IShoppingCartService {

	@Resource
	private ShoppingCartMapper shoppingCartMapper;
	@Resource
	private UserPrivilegeMapper userPrivilegeMapper;

	@Override
	protected IBaseDao<ShoppingCart> getMapper() {
		return shoppingCartMapper;
	}

	@Override
	public ShoppingCart queryShoppingCartByUserIdAndPriceId(
			ShoppingCart shoppingCart) {
		return shoppingCartMapper
				.queryShoppingCartByUserIdAndPriceId(shoppingCart);
	}

	@Override
	public int queryCountGoodsByUserIdAndGoodsId(ShoppingCart shoppingCart) {
		return shoppingCartMapper
				.queryCountGoodsByUserIdAndGoodsId(shoppingCart);
	}

	@Override
	public List<ShoppingCart> queryAllBuyShoppingCartByUserId(int userId) {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setUserId(userId);
		shoppingCart.setIsBuy(2);// 2表示不购买
		return shoppingCartMapper
				.queryAllNotIsBuyShoppingCartByUserId(shoppingCart);

	}

	@Override
	public List<ShoppingCart> queryAllShoppingCartByUserId(int userId) {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setUserId(userId);
		shoppingCart.setIsBuy(3);// 1表示买 2表示不购买
		return shoppingCartMapper
				.queryAllNotIsBuyShoppingCartByUserId(shoppingCart);
	}

	@Override
	public int delAllBuyShoppingCartByUserId(int userId) {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setUserId(userId);
		shoppingCart.setIsBuy(2);// 1表示买 2表示不购买
		return shoppingCartMapper
				.delAllNotIsBuyShoppingCartByUserId(shoppingCart);
	}

	@Override
	public int delAllShoppingCartByUserId(int userId) {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setUserId(userId);
		shoppingCart.setIsBuy(3);// 1表示买 2表示不购买
		return shoppingCartMapper
				.delAllNotIsBuyShoppingCartByUserId(shoppingCart);
	}

	@Override
	public int delAllShoppingCartByUserIdAndPriceIds(int userId, String priceIds) {
		return shoppingCartMapper.delAllShoppingCartByUserIdAndPriceIds(userId,
				priceIds);
	}

	public ShoppingCartMapper getShoppingCartMapper() {
		return shoppingCartMapper;
	}

	public void setShoppingCartMapper(ShoppingCartMapper shoppingCartMapper) {
		this.shoppingCartMapper = shoppingCartMapper;
	}

	@Override
	public ShoppingCartVo queryShoppingCartInfo(User user) {
		// 获取用户的优惠信息
		UserPrivilege userPrivilege = userPrivilegeMapper.selectByUserId(user
				.getUserId());

		boolean isWholeSalePrice = false;
		boolean isDiscount = false;
		int discount = 100;
		if (null != userPrivilege) {
			Byte isWholeSalePriceByte = userPrivilege.getIsWholeSalePrice();
			if (null != isWholeSalePriceByte && isWholeSalePriceByte == 1) {
				isWholeSalePrice = true;
			}
			Byte isDiscountByte = userPrivilege.getIsDiscount();
			if (null != isDiscountByte && isDiscountByte == 1) {
				isDiscount = true;
			}
			Integer discountInt = userPrivilege.getDiscount();
			if (isDiscount && null != discountInt) {
				if (discountInt > 100) {
					discount = 100;
				} else if (discountInt <= 0) {
					discount = 0;
				} else {
					discount = discountInt;
				}
			}
		}

		ShoppingCartVo shoppingCartVo = new ShoppingCartVo();
		int goodsNum = 0;
		int totalAmount = 0;
		int needPayAmount = 0;
		int discoiuntAmount = 0;

		int price = 0;
		int retailPrice = 0;
		int unitGoodNum = 0;

		List<ShoppingCartItem> shoppingCartItems = shoppingCartMapper
				.queryBuyShopCartItemByUserId(user.getUserId());
		if (null != shoppingCartItems && shoppingCartItems.size() > 0) {
			shoppingCartVo.setShoppingCartItem(shoppingCartItems);

			GoodsPrice goodsPrice = null;
			for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
				goodsPrice = shoppingCartItem.getGoodsPrice();

				goodsNum += shoppingCartItem.getQuantity();
				unitGoodNum = shoppingCartItem.getQuantity();
				retailPrice = goodsPrice.getRetailPrice();
				if (isWholeSalePrice) {
					price = goodsPrice.getWholesalePrice();
					retailPrice=goodsPrice.getWholesalePrice();
				} else {
					price = goodsPrice.getRetailPrice();
				}
				totalAmount += retailPrice * unitGoodNum;
				needPayAmount += (price * discount / 100) * unitGoodNum;
				;
			}
			
		}
		shoppingCartVo.setGoodsNum(goodsNum);
		shoppingCartVo.setNeedPayAmount(needPayAmount);
		shoppingCartVo.setTotalAmount(totalAmount);
		discoiuntAmount = totalAmount = needPayAmount;
		shoppingCartVo.setDiscoiuntAmount(discoiuntAmount);

		return shoppingCartVo;
	}

	@Override
	public PageResult<ShoppingCartDetailVO> queryShoppingCartDetailVOByUserId(
			PageResult<ShoppingCartDetailVO> t, int userId) {
		int pageNo = t.getPageNo();
		int pageSize = t.getPageSize();
		pageNo = pageNo == 0 ? 1 : pageNo;
		pageSize = pageSize == 0 ? 10 : pageSize;
		PageHelper.startPage(pageNo, pageSize);

		return PageResult
				.toPageResult(shoppingCartMapper
						.queryShoppingCartDetailVOByUserId(userId), t);
	}

	@Override
	public int updateAllIsBuyStateByUserIdAndisBuyValue(
			ShoppingCart shoppingCart) {
		return shoppingCartMapper
				.updateAllIsBuyStateByUserIdAndisBuyValue(shoppingCart);
	}

}
