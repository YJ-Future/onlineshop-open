package com.gloryofme.onlineshop.manage.controller;

import com.gloryofme.onlineshop.common.bean.EasyUIResult;
import com.gloryofme.onlineshop.manage.pojo.Item;
import com.gloryofme.onlineshop.manage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by YU on 2016/7/4.
 */
@RequestMapping("/item")
@Controller
public class ItemController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addItem(Item item, @RequestParam("desc") String desc, @RequestParam("itemParams") String itemParam) {
        if (this.itemService.insertItem(item, desc, itemParam) > 0) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getItemList(@RequestParam("page") int page, @RequestParam("rows") int rows){
        List<Item> items = this.itemService.selectPage(page, rows);
        EasyUIResult<Item> result = new EasyUIResult<>();
        result.setRows(items);
        result.setTotal(this.itemService.selectCount());
        return ResponseEntity.ok(result);
    }



}
