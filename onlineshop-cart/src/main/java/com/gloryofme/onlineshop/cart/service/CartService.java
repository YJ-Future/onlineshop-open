package com.gloryofme.onlineshop.cart.service;

import com.gloryofme.onlineshop.cart.pojo.Cart;

import java.util.List;

/**
 * @author gloryzyf<bupt_zhuyufei@163.com>
 */
public interface CartService {

    boolean add(Cart cart);

    List<Cart> getCartByToken(String cartToken);

    boolean updateCartItemNum(Cart cart);

    boolean updateNumByUserIdAndItemId(Long userId,Long itemId,Integer num);

    boolean deleteByUserIdAndItemId(Long userId,Long itemId);

    List<Cart> getCartByUserId(Long userId);

    Cart getCartByUserIdAndItemId(Long userId,Long itemId);

}
