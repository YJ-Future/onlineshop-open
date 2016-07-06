package com.gloryofme.onlineshop.manage.controller;

import com.gloryofme.onlineshop.manage.pojo.Content;
import com.gloryofme.onlineshop.manage.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by YU on 2016/7/6.
 */
@RequestMapping("/content")
@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getPageByCategoryId(@RequestParam("categoryId") Long categoryId, @RequestParam("page") int page, @RequestParam("rows") int rows){
        List<Content>  contents = this.contentService.selectPageByCatId(categoryId, page, rows);

        return ResponseEntity.ok(contents);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addContent(Content content){
        if(this.contentService.insert(content) > 0 )
            return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
