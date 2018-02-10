package io.clab.mpf.shop.service.common.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import io.clab.mpf.shop.entity.goodsprice.GoodsPrice;
import io.clab.mpf.shop.entity.shoppingcart.ShoppingCart;
import io.clab.mpf.shop.entity.shoppingcart.ShoppingCartDetailVO;
import io.clab.mpf.shop.entity.user.User;
import io.clab.mpf.shop.entity.user.UserPrivilege;
import io.clab.mpf.shop.service.common.ICommonExchangeService;
import io.clab.mpf.shop.service.goodsprice.IGoodsPriceService;
import io.clab.mpf.shop.service.shoppingcart.IShoppingCartService;
import io.clab.mpf.shop.service.user.IUserPrivilegeService;
import io.clab.mpf.shop.vo.common.TotalPriveAndCategory;

@Service
public class CommonExchangeServiceImpl implements ICommonExchangeService {
	@Resource
	private IShoppingCartService shoppingCartService;

	@Resource
	private IGoodsPriceService goodsPriceService;

	@Resource
	private IUserPrivilegeService userPrivilegeService;

	/**
	 * 依据用户权限，获取换算后的销售价格和购物车中的条目数； 显示的实际价放在零售价字段里（依据用户不同价格不一样），购物车中的数量放在批发价字段里
	 * 如果用户不存在，则不改变销售价，购物车中数量改为0
	 * 
	 * @param user
	 *            当前用户，
	 * @param listGoodsPrice
	 *            规格列表
	 * @return
	 */
	public List<GoodsPrice> getCurrentUserGoodsprice(User user,
			List<GoodsPrice> listGoodsPrice) {
		if (listGoodsPrice == null || listGoodsPrice.size() == 0) {
			return listGoodsPrice;
		}
		if (user == null) {
			// 用户未登录，购物车数量全部为0,实际价就是零售价
			for (int i = 0; i < listGoodsPrice.size(); i++) {
				listGoodsPrice.get(i).setBuyPrice(0);
			}
		} else {
			// 用户已登录，需要查询购物车里规格数量，以及换算实际价
			UserPrivilege userPrivilege = userPrivilegeService
					.getUserWholeSalePriceAndDiscountPrivate(user);
			int powerPF = userPrivilege.getIsWholeSalePrice();// 享受批发价
			int rebate = userPrivilege.getDiscount();// 折扣

			for (int y = 0; y < listGoodsPrice.size(); y++) {
				ShoppingCart shoppingCart = new ShoppingCart();
				shoppingCart.setUserId(user.getUserId());
				int retailPrice = 0;
				if (powerPF == 1) {
					// 享受批发价
					retailPrice = listGoodsPrice.get(y).getWholesalePrice();
				} else {
					// 不享受批发价
					retailPrice = listGoodsPrice.get(y).getRetailPrice();
				}

				listGoodsPrice.get(y)
						.setRetailPrice(retailPrice * rebate / 100);// 零售价再乘以折扣
				// 获取购物车中的数量
				shoppingCart.setPriceId(listGoodsPrice.get(y).getPriceId());
				int count = 0;
				shoppingCart = shoppingCartService
						.queryShoppingCartByUserIdAndPriceId(shoppingCart);
				if (shoppingCart != null) {
					count = shoppingCart.getQuantity();
				}
				listGoodsPrice.get(y).setBuyPrice(count);
			}
		}
		return listGoodsPrice;
	};

	/**
	 * 依据用户id和购物车条目，获取换算后的总金额; 如果用户不存在，则返回0; 该方法不要频繁调用
	 * 
	 * @param user
	 *            当前用户
	 * @param listShoppingCart
	 *            购物条目
	 * @return
	 */
	public TotalPriveAndCategory getCurrentUserTotalPrice(User user,
			List<ShoppingCart> listShoppingCart) {
		TotalPriveAndCategory totalPriveAndCategory=new TotalPriveAndCategory();
		int totalPrice = 0;
		int totalCategory=0;
		if (user == null || listShoppingCart == null
				|| listShoppingCart.size() <= 0) {
			totalPriveAndCategory.setTotalPrice(totalPrice);
			totalPriveAndCategory.setTotalCategory(totalCategory);
			return totalPriveAndCategory;
		}
		// 用户已登录，需要查询购物车里规格数量，以及换算实际价
		UserPrivilege userPrivilege = userPrivilegeService
				.getUserWholeSalePriceAndDiscountPrivate(user);
		int powerPF = userPrivilege.getIsWholeSalePrice();// 享受批发价
		int rebate = userPrivilege.getDiscount();// 折扣
		for (int i = 0; i < listShoppingCart.size(); i++) {
			ShoppingCart tempSh = listShoppingCart.get(i);
			GoodsPrice tempGp = goodsPriceService.selectByPrimaryKey(tempSh
					.getPriceId());

			int retailPrice = 0;
			if (powerPF == 1) {
				// 享受批发价
				retailPrice = tempGp.getWholesalePrice();
			} else {
				// 不享受批发价
				retailPrice = tempGp.getRetailPrice();
			}

			totalPrice = totalPrice + retailPrice * rebate / 100
					* tempSh.getQuantity();// 零售价再乘以折扣再乘以数量
			totalCategory=totalCategory+tempSh.getQuantity();

		}
		totalPriveAndCategory.setTotalPrice(totalPrice);
		totalPriveAndCategory.setTotalCategory(totalCategory);
		return totalPriveAndCategory;
	}

	@Override
	public List<ShoppingCartDetailVO> getCurrentUserGoodspriceForShoppingCartDetailVO(
			User user, List<ShoppingCartDetailVO> listShoppingCartDetailVO) {
		if (listShoppingCartDetailVO == null
				|| listShoppingCartDetailVO.size() == 0 || user == null) {
			return listShoppingCartDetailVO;
		}

		// 用户已登录，需要查询购物车里规格数量，以及换算实际价
		UserPrivilege userPrivilege = userPrivilegeService
				.getUserWholeSalePriceAndDiscountPrivate(user);
		int powerPF = userPrivilege.getIsWholeSalePrice();// 享受批发价
		int rebate = userPrivilege.getDiscount();// 折扣

		for (int y = 0; y < listShoppingCartDetailVO.size(); y++) {

			int retailPrice = 0;
			if (powerPF == 1) {
				// 享受批发价
				retailPrice = listShoppingCartDetailVO.get(y)
						.getWholesalePrice();
			} else {
				// 不享受批发价
				retailPrice = listShoppingCartDetailVO.get(y).getRetailPrice();
			}

			listShoppingCartDetailVO.get(y).setRetailPrice(
					retailPrice * rebate / 100);// 零售价再乘以折扣

		}
		return listShoppingCartDetailVO;
	};

}
