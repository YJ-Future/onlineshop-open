package com.gloryofme.onlineshop.cart.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.gloryofme.onlineshop.cart.pojo.Cart;
import com.gloryofme.onlineshop.cart.threadlocal.CartTokenThreadLocal;
import com.gloryofme.onlineshop.cart.threadlocal.UserThreadLocal;
import com.gloryofme.onlineshop.cart.service.CartService;
import com.gloryofme.onlineshop.common.pojo.HttpResult;
import com.gloryofme.onlineshop.common.pojo.PageResult;
import com.gloryofme.onlineshop.common.service.RedisService;
import com.gloryofme.onlineshop.manage.pojo.Item;
import com.gloryofme.onlineshop.manage.service.ItemService;
import com.gloryofme.onlineshop.sso.pojo.User;
import com.gloryofme.onlineshop.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车
 * 数据库和Cookie两方面
 *
 * @author gloryzyf<bupt_zhuyufei@163.com>
 */
@RequestMapping("/cart")
@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @RequestMapping(value = "/add/{itemId}/{itemNum}", method = RequestMethod.POST)
    public String addCart(@PathVariable("itemId") Long itemId, @PathVariable("itemNum") Integer itemNum) {
        //判断用户是否登陆
        User user = UserThreadLocal.get();
        String cartToken = CartTokenThreadLocal.get();
        List<Cart> carts = cartService.getCartByToken(cartToken);
        if (user == null) {//未登录或超时
            if (carts == null) {
                carts = new ArrayList<>();
            }
            //判断用户添加商品之前购物车中是否包括该商品
            boolean flag = false;
            for (Cart ct : carts) {
                if (ct.getItemId() == itemId) {
                    Integer num = ct.getItemNum();
                    ct.setItemNum(num + itemNum);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                Cart cart = new Cart();
                cart.setItemId(itemId);
                cart.setItemNum(itemNum);
                //使用item rpc 获取加入购物车商品的信息
                Item item = itemService.selectById(itemId);
                cart.setItemImage(item.getImage());
                cart.setItemPrice(item.getPrice());
                cart.setItemTitle(item.getTitle());
                carts.add(cart);
            }
            //添加到redis缓存中
            String jsonData = null;
            try {
                jsonData = MAPPER.writeValueAsString(carts);
                redisService.set(cartToken, jsonData);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {//登陆状态
            //获取cartToken对应的redis 购物车（首次合并 redis中可能有数据 若不是首次合并 则数据为空）
            List<Cart> cartsInDB = cartService.getCartByUserId(user.getId());
            if (carts == null || carts.size() == 0) {
                carts = new ArrayList<>();
            }
            Cart cart = new Cart();
            cart.setItemId(itemId);
            cart.setItemNum(itemNum);
            Item item = itemService.selectById(itemId);
            cart.setItemImage(item.getImage());
            cart.setItemPrice(item.getPrice());
            cart.setItemTitle(item.getTitle());
            carts.add(cart);
            boolean inFlag = false;
            //合并购物车
            //暂时方案合并结果是存入到数据库中
            //清空redis中的数据
            redisService.set(cartToken, "");
            List<Cart> mergedCarts = new ArrayList<>();
            for (Cart ct : carts) {
                inFlag = false;
                for (Cart dbCt : cartsInDB) {
                    mergedCarts.add(dbCt);
                    if (ct.getItemId() == dbCt.getItemId()) {
                        int mgNum = ct.getItemNum() + dbCt.getItemNum();
                        cartService.updateNumByUserIdAndItemId(user.getId(), ct.getItemId(), mgNum);
                        dbCt.setItemNum(mgNum);
                        inFlag = true;
                        break;
                    } else {
                        mergedCarts.add(ct);
                    }
                }
                if (!inFlag) {
                    cartService.add(ct);
                }
            }
        }
        //跳转到添加购物车成功界面
        //模仿京东连接
        //pid :（productId） 商品ID
        //pc : (product count) 商品数量
        return "redirect:/addToCart.html?pid=" + itemId + "pc=" + itemNum;
    }


    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public PageResult query() {
        User user = UserThreadLocal.get();
        PageResult result = new PageResult();
        String cartToken = CartTokenThreadLocal.get();
        if(user == null){
            //redis中读取购物车
            List<Cart> carts = cartService.getCartByToken(cartToken);
            result.setData(carts);
            result.setTotal(Long.valueOf(carts.size()));
        }else{
            //判断cartToken
            //redis中数据非空，代表未合并，进行数据合并
            //清空redis cartToken对应的数据
            //更新数据库
            //返回数据
            Long userId = user.getId();
            List<Cart> carts = cartService.getCartByUserId(userId);
            Page page = (Page) carts;
            result.setData(carts);
            result.setTotal(page.getTotal());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/update/num", method = RequestMethod.POST)
    public HttpResult updateItemNum(@RequestParam("itemId") Long itemId, @RequestParam("itemNum") Integer num) {
        HttpResult result = new HttpResult();
        //判断用户是否登陆
        User user = UserThreadLocal.get();
        String cartToken = CartTokenThreadLocal.get();
        if (user == null) {
            List<Cart> carts = cartService.getCartByToken(cartToken);
            for (Cart cart : carts) {
                if (cart.getItemId() == itemId) {
                    cart.setItemNum(num);
                    break;
                }
            }
            String jsonData = null;
            try {
                jsonData = MAPPER.writeValueAsString(carts);
                redisService.set(cartToken, jsonData);
                result.setCode(201);
                result.setContent("修改成功");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            Long userId = user.getId();
            if (cartService.updateNumByUserIdAndItemId(userId, itemId, num)) {
                result.setCode(201);
                result.setContent("修改成功");
            } else {
                result.setCode(202);
                result.setContent("修改失败");
            }
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public HttpResult deleteCart(@RequestParam("userId") Long userId, @RequestParam("itemId") Long itemId) {
        HttpResult result = new HttpResult();
        //判断用户是否登陆
        User user = UserThreadLocal.get();
        String cartToken = CartTokenThreadLocal.get();
        if (user == null) {
            List<Cart> carts = cartService.getCartByToken(cartToken);
            List<Cart> res = new ArrayList<>();
            for (Cart cart : carts) {
                if (cart.getItemId() != itemId) {
                    res.add(cart);
                }
            }
            String jsonData = null;
            try {
                jsonData = MAPPER.writeValueAsString(res);
                redisService.set(cartToken, jsonData);
                result.setCode(201);
                result.setContent("删除成功");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            if (cartService.deleteByUserIdAndItemId(userId, itemId)) {
                result.setCode(201);
                result.setContent("删除成功");
            } else {
                result.setCode(202);
                result.setContent("删除失败");
            }
        }
        return result;
    }
}
