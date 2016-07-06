package com.gloryofme.onlineshop.manage.controller;

import com.gloryofme.onlineshop.common.bean.TreeNodeResult;
import com.gloryofme.onlineshop.manage.pojo.ContentCategory;
import com.gloryofme.onlineshop.manage.service.ContentCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YU on 2016/7/6.
 */
@RequestMapping("/content/category")
@Controller
public class ContentCatController {
    @Autowired
    private ContentCatService catService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getContentCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId){
        List<ContentCategory> categorys = this.catService.selectContentCatList(parentId);
        List<TreeNodeResult> result = new ArrayList<>();
        TreeNodeResult treeNode = null;
        for(ContentCategory category: categorys){
            treeNode = new TreeNodeResult();
            treeNode.setText(category.getName());
            treeNode.setId(category.getId());
            treeNode.setState(category.getIsParent()? "closed" : "open");
            result.add(treeNode);
        }
        return ResponseEntity.ok(result);
    }

}
