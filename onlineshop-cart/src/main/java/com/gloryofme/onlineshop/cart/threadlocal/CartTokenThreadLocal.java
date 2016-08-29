package com.gloryofme.onlineshop.cart.threadlocal;

/**
 * 购物车token信息
 *
 * @author gloryzyf<bupt_zhuyufei@163.com>
 */
public class CartTokenThreadLocal {

    private static ThreadLocal<String> cartThreadLocal = new ThreadLocal<>();

    public static String get() {
        return cartThreadLocal.get();
    }

    public static void set(String token) {
        cartThreadLocal.set(token);
    }

    public static void unset() {
        cartThreadLocal.remove();
    }
}
