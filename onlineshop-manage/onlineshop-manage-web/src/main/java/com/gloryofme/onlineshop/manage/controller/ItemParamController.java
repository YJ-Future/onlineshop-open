package com.gloryofme.onlineshop.manage.controller;

import com.gloryofme.onlineshop.common.bean.EasyUIResult;
import com.gloryofme.onlineshop.manage.pojo.ItemParam;
import com.gloryofme.onlineshop.manage.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by YU on 2016/7/5.
 */
@RequestMapping("/item/param")
@Controller
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getItemParamPage(@RequestParam("page") int page, @RequestParam("rows") int rows) {
        List<ItemParam> params = this.itemParamService.selectPage(page, rows);
        EasyUIResult<ItemParam> result = new EasyUIResult<>();
        result.setRows(params);
        result.setTotal(this.itemParamService.selectCount());
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "{itemCatId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getItemParamByItemCatId(@PathVariable("itemCatId") Long itemCatId) {
        List<ItemParam> params = this.itemParamService.selectByItemCatId(itemCatId);
        return params != null && !params.isEmpty() ? ResponseEntity.ok(params.get(0)) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(value = "{itemCatId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity insertItemParam(@PathVariable("itemCatId") Long itemCatId, @RequestParam("paramData") String paramData) {
        ItemParam param = new ItemParam();
        param.setItemCatId(itemCatId);
        param.setParamData(paramData);
        if (itemParamService.insert(param) > 0)
            return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity insertItemParam(String  ids) {
        for(String itemCatId : ids.split(",")){
            this.itemParamService.deleteById(Long.valueOf(itemCatId));
        }
        return ResponseEntity.ok().build();
    }
}
