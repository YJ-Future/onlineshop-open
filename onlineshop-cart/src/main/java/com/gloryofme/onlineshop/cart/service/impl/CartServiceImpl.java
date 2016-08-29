package com.gloryofme.onlineshop.cart.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gloryofme.onlineshop.cart.mapper.CartMapper;
import com.gloryofme.onlineshop.cart.pojo.Cart;
import com.gloryofme.onlineshop.cart.service.CartService;
import com.gloryofme.onlineshop.common.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author gloryzyf<bupt_zhuyufei@163.com>
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private RedisService redisService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final JavaType cartListType = MAPPER.getTypeFactory().constructParametricType(List.class, Cart.class);

    public List<Cart> getCartByToken(String cartToken) {
        if (StringUtils.isEmpty(cartToken))
            return null;
        String jsonData = redisService.get(cartToken);
        if (StringUtils.isEmpty(jsonData))
            return null;
        try {
            List<Cart> carts = MAPPER.readValue(jsonData, cartListType);
            return carts;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(Cart cart) {
        Cart record = getCartByUserIdAndItemId(cart.getUserId(), cart.getItemId());
        if (record == null) { //数据库购物车记录中没有此商品
            //id 策略 自增
            return cartMapper.add(cart) > 0;
        } else {//新添加购物车商品
            record.setItemNum(cart.getItemNum() + record.getItemNum());
            return updateCartItemNum(record);
        }
    }

    @Override
    public boolean updateCartItemNum(Cart cart) {
        return cartMapper.updateCartItemNum(cart) > 0;
    }

    @Override
    public boolean updateNumByUserIdAndItemId(Long userId, Long itemId, Integer num) {
        return cartMapper.updateNumByUserIdAndItemId(userId, itemId, num) > 0;
    }

    @Override
    public boolean deleteByUserIdAndItemId(Long userId, Long itemId) {
        return cartMapper.deleteByUserIdAndItemId(userId, itemId) > 0;
    }

    @Override
    public List<Cart> getCartByUserId(Long userId) {
        return cartMapper.getCartByUserId(userId);
    }

    @Override
    public Cart getCartByUserIdAndItemId(Long userId, Long itemId) {
        return cartMapper.getCartByUserIdAndItemId(userId, itemId);
    }
}
