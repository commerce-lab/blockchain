package io.clab.mpf.shop.dao.shoppingcart;

import java.util.List;

import io.clab.mpf.shop.dao.base.IBaseDao;
import io.clab.mpf.shop.entity.shoppingcart.ShoppingCart;
import io.clab.mpf.shop.entity.shoppingcart.ShoppingCartDetailVO;
import io.clab.mpf.shop.entity.shoppingcart.ShoppingCartItem;

/**
 *
 * 购物车条目dao
 */
public interface ShoppingCartMapper extends IBaseDao<ShoppingCart> {
	/**
	 * 依据用户id和规格id，查询购物条目
	 * 
	 * @param shoppingCart
	 * @return
	 */
	public ShoppingCart queryShoppingCartByUserIdAndPriceId(
			ShoppingCart shoppingCart);

	/**
	 * 依据用户id和商品id，查询用户买了多少种类商品
	 * 
	 * @param shoppingCart
	 * @return
	 */
	public int queryCountGoodsByUserIdAndGoodsId(ShoppingCart shoppingCart);

	/**
	 * 依据用户id和非购买标志，查询所有购物条目
	 * 
	 * @param shoppingCart
	 * @return
	 */
	public List<ShoppingCart> queryAllNotIsBuyShoppingCartByUserId(
			ShoppingCart shoppingCart);

	/**
	 * 依据用户id和非购买标志，删除所有相关数据
	 * 
	 * @param shoppingCart
	 * @return
	 */
	public int delAllNotIsBuyShoppingCartByUserId(ShoppingCart shoppingCart);

	/**
	 * 依据用户id和规格id，删除所有相关数据
	 * 
	 * @param shoppingCart
	 * @return
	 */
	public int delAllShoppingCartByUserIdAndPriceIds(int userId, String pricesId);

	/**
	 * 根据用户id查询所购买的购物车Item列表
	 * 
	 * @param userId
	 * @return
	 */
	List<ShoppingCartItem> queryBuyShopCartItemByUserId(int userId);

	/**
	 * 依据用户id，查询购物车勾选VO
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	public List<ShoppingCartDetailVO> queryShoppingCartDetailVOByUserId(
			int userId);

	/**
	 * 依据shoppingCart.userId,将该用户的所有购物规格条目状态置为shoppingCart.isBuy
	 * 
	 * @param shoppingCart
	 *            有用的参数是userId和isBuy
	 * @return
	 */
	public int updateAllIsBuyStateByUserIdAndisBuyValue(
			ShoppingCart shoppingCart);
}