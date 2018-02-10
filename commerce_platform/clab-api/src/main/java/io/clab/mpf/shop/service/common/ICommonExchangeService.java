package io.clab.mpf.shop.service.common;

import java.util.List;

import io.clab.mpf.shop.entity.goodsprice.GoodsPrice;
import io.clab.mpf.shop.entity.shoppingcart.ShoppingCart;
import io.clab.mpf.shop.entity.shoppingcart.ShoppingCartDetailVO;
import io.clab.mpf.shop.entity.user.User;
import io.clab.mpf.shop.vo.common.TotalPriveAndCategory;

public interface ICommonExchangeService {
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
			List<GoodsPrice> listGoodsPrice);

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
			List<ShoppingCart> listShoppingCart);
	
	/**
	 * 依据用户权限，获取换算后的销售价格； 显示的实际价放在零售价字段里（依据用户不同价格不一样）
	 * 如果用户不存在，则不改变销售价
	 * 
	 * @param user
	 *            当前用户，
	 * @param listShoppingCartDetailVO
	 *            购物车条目VO，用于勾选
	 * @return
	 */
	public List<ShoppingCartDetailVO> getCurrentUserGoodspriceForShoppingCartDetailVO(User user,
			List<ShoppingCartDetailVO> listShoppingCartDetailVO);

}
