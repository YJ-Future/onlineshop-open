package com.gloryofme.onlineshop.cart.interceptor;

import com.gloryofme.onlineshop.cart.threadlocal.CartTokenThreadLocal;
import com.gloryofme.onlineshop.cart.threadlocal.UserThreadLocal;
import com.gloryofme.onlineshop.common.utils.CookieUtils;
import com.gloryofme.onlineshop.sso.pojo.User;
import com.gloryofme.onlineshop.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 登陆状态拦截器
 *
 * @author gloryzyf<bupt_zhuyufei@163.com>
 */
public class LoginInterceptor implements HandlerInterceptor {

    public static final String COOKIE_USER = "OL_SSO_TICKET_";//对应之前的SSO模块中的Token

    public static final String COOKIE_CART = "OL_CART_KEY";

    public static final int CART_TOKE_EXPIRE = 30 * 24 * 60 * 60;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        UserThreadLocal.unset();
        String cartToken = CookieUtils.getCookieValue(request, COOKIE_CART);
        if (StringUtils.isEmpty(cartToken)) {//客户端没有token
            //token的生成策略 uuid
            cartToken = UUID.randomUUID().toString();
            CartTokenThreadLocal.set(cartToken);
            CookieUtils.setCookie(request, response, COOKIE_CART, cartToken, CART_TOKE_EXPIRE, true);
        }
        String userToken = CookieUtils.getCookieValue(request, COOKIE_USER);
        if (StringUtils.isEmpty(userToken))
            return true;
        User user = userService.getUserByTicket(userToken);
        if (user == null)
            return true;
        UserThreadLocal.set(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
