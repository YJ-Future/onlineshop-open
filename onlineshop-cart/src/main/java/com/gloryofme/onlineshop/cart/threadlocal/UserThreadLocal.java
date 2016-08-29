package com.gloryofme.onlineshop.cart.threadlocal;

import com.gloryofme.onlineshop.sso.pojo.User;

/**
 * 保存用户登陆信息
 *
 * @author gloryzyf<bupt_zhuyufei@163.com>
 */
public class UserThreadLocal {

    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public static void set(User user) {
        userThreadLocal.set(user);
    }

    public static User get() {
        return userThreadLocal.get();
    }

    public static void unset() {
        userThreadLocal.remove();
    }
}
