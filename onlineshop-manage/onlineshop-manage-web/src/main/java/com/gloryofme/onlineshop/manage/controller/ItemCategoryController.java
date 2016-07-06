package com.gloryofme.onlineshop.manage.controller;

import com.gloryofme.onlineshop.common.bean.EasyUIResult;
import com.gloryofme.onlineshop.common.bean.TreeNodeResult;
import com.gloryofme.onlineshop.common.service.RedisService;
import com.gloryofme.onlineshop.manage.pojo.ItemCatResult;
import com.gloryofme.onlineshop.manage.pojo.ItemCategory;
import com.gloryofme.onlineshop.manage.service.ItemCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YU on 2016/7/2.
 */
@RequestMapping("item/category")
@RestController
public class ItemCategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemCategoryController.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private ItemCategoryService itemCatService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public ItemCatResult selectAllItemCats(){
        ItemCatResult result =  itemCatService.selectAllItemCat();
        return result;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public  List<TreeNodeResult> selectItemCategoryList(@RequestParam(value = "id", defaultValue = "0") Long parentId){
        List<ItemCategory> catList = itemCatService.selectItemCategoryList(parentId);
        List<TreeNodeResult> result = new ArrayList<>();
        TreeNodeResult treeNode = null;
        for(ItemCategory category: catList){
            treeNode = new TreeNodeResult();
            treeNode.setId(category.getId());
            treeNode.setText(category.getName());
            treeNode.setState(category.getIsParent()? "closed" : "open");
            result.add(treeNode);
        }
        return result;
    }

}
