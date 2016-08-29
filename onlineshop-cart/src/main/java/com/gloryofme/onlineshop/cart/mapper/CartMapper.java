package com.gloryofme.onlineshop.cart.mapper;

import com.gloryofme.onlineshop.cart.pojo.Cart;

import java.util.List;

/**
 * @author gloryzyf<bupt_zhuyufei@163.com>
 */
public interface CartMapper {

    int add(Cart cart);

    int updateCartItemNum(Cart cart);

    int updateNumByUserIdAndItemId(Long userId,Long itemId,Integer num);

    int deleteByUserIdAndItemId(Long userId,Long itemId);

    List<Cart> getCartByUserId(Long userId);

    Cart getCartByUserIdAndItemId(Long userId,Long itemId);

}
