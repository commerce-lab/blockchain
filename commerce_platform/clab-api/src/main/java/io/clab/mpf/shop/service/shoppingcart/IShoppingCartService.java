package io.clab.mpf.shop.service.shoppingcart;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import io.clab.mpf.shop.entity.shoppingcart.ShoppingCart;
import io.clab.mpf.shop.entity.shoppingcart.ShoppingCartDetailVO;
import io.clab.mpf.shop.entity.shoppingcart.ShoppingCartVo;
import io.clab.mpf.shop.entity.user.User;
import io.clab.mpf.shop.service.base.IBaseService;
import util.PageResult;

/**
 * 购物车条目接口
 */
public interface IShoppingCartService extends IBaseService<ShoppingCart> {
	/**
	 * 依据用户id和规格id，查询购物条目
	 * 
	 * @param shoppingCart
	 * @return
	 */
	public ShoppingCart queryShoppingCartByUserIdAndPriceId(ShoppingCart shoppingCart);

	/**
	 * 依据用户id和商品id，查询用户买了多少种类商品
	 * 
	 * @param shoppingCart
	 * @return
	 */
	public int queryCountGoodsByUserIdAndGoodsId(ShoppingCart shoppingCart);

	/**
	 * 依据用户id，查询所有准备购买的购物条目
	 * 
	 * @param shoppingCart
	 * @return
	 */
	public List<ShoppingCart> queryAllBuyShoppingCartByUserId(int userId);

	/**
	 * 依据用户id，查询购物车中的所有购物条目
	 * 
	 * @param shoppingCart
	 * @return
	 */
	public List<ShoppingCart> queryAllShoppingCartByUserId(int userId);

	/**
	 * 依据用户id，删除所有准备购买的购物条目
	 * 
	 * @param shoppingCart
	 * @return
	 */
	public int delAllBuyShoppingCartByUserId(int userId);
	
	/**
	 * 依据用户id，删除所有购物条目
	 * 
	 * @param shoppingCart
	 * @return
	 */
	public int delAllShoppingCartByUserId(int userId);
	
	/**
	 * 依据用户id,规格id，删除购物条目
	 * 
	 * @param shoppingCart
	 * @return
	 */
	
	public int delAllShoppingCartByUserIdAndPriceIds(int userId,String priceIds);
	

	/**
	 * 查询当前用户的购买的商品信息
	 * 
	 * @param user
	 * @return
	 */
	ShoppingCartVo queryShoppingCartInfo(User user);

	
	/**
	 * 依据用户id，查询购物车勾选VO
	 * @param userId 用户id
	 * @return
	 */
	public PageResult<ShoppingCartDetailVO> queryShoppingCartDetailVOByUserId(@Param("t")PageResult<ShoppingCartDetailVO> t,@Param("userId")int  userId);

	/**
	 * 依据shoppingCart.userId,将该用户的所有购物规格条目状态置为shoppingCart.isBuy
	 * @param shoppingCart 有用的参数是userId和isBuy
	 * @return
	 */
	public int updateAllIsBuyStateByUserIdAndisBuyValue(ShoppingCart shoppingCart);
}
