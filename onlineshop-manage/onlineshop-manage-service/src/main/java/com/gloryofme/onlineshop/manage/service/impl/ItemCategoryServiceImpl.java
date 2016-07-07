package com.gloryofme.onlineshop.manage.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gloryofme.onlineshop.common.service.RedisService;
import com.gloryofme.onlineshop.manage.mapper.ItemCategoryMapper;
import com.gloryofme.onlineshop.manage.pojo.ItemCatData;
import com.gloryofme.onlineshop.manage.pojo.ItemCatResult;
import com.gloryofme.onlineshop.manage.pojo.ItemCategory;
import com.gloryofme.onlineshop.manage.pojo.ItemCategoryResult;
import com.gloryofme.onlineshop.manage.service.ItemCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类目实现类
 * Created by YU on 2016/7/2.
 */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemCategoryServiceImpl.class);

    @Autowired(required = false)
    private ItemCategoryMapper categoryMapper;
    @Autowired
    private RedisService redisService;

    //redis key 命名规范：项目_模块_业务_ID
    private static final String REDIS_KEY_ITEMCAT = "ONLINESHOP_MANAGE_WEB_ITEMCAT_";

    private static final Integer REDIS_EXPIRE_TIME = 60 * 60 * 2;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 获取对应ID的所有子类目
     * @param parentId
     * @return
     */
    @Override
    public List<ItemCategory> selectItemCategoryList(Long parentId) {
        //缓存取
        String redisKey = REDIS_KEY_ITEMCAT+parentId;
        try{
            JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class,ItemCategory.class);
            String value = redisService.get(redisKey);
            if(StringUtils.isNoneBlank(value)){
                try {
                    return MAPPER.readValue(value, javaType);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }catch(Exception e){
            LOGGER.debug("redis service item category service error");
        }
        //数据库取
        ItemCategory category = new ItemCategory();
        category.setParentId(parentId);
        List<ItemCategory> result =  categoryMapper.select(category);
        //缓存存
        try {
            redisService.set(redisKey, MAPPER.writeValueAsString(result), REDIS_EXPIRE_TIME);
        } catch (JsonProcessingException e) {
            LOGGER.debug("redis service item category service error");
        }
        return result;
    }

    /**
     * 获取所有的商品类目
     *
     * @return
     */
    @Override
    public ItemCatResult selectAllItemCat() {
        //首先从redis中获取类目树
        String value = redisService.get(REDIS_KEY_ITEMCAT);
        try {
            if (StringUtils.isNoneBlank(value)){
                ItemCatResult redisResult = MAPPER.readValue(value, ItemCatResult.class);
                return redisResult;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        //生成类目树
        List<ItemCategory> categories = categoryMapper.select(null);
        //类目的集合
        Map<Long, List<ItemCategory>> map = new HashMap<>();
        for (ItemCategory category : categories) {
            if (!map.containsKey(category.getParentId()))
                map.put(category.getParentId(), new ArrayList<ItemCategory>());
            map.get(category.getParentId()).add(category);
        }
        //封装结果
        ItemCatResult result = new ItemCatResult();

        List<ItemCategory> itemCatParent = map.get(Long.valueOf(0));
        for (ItemCategory category : itemCatParent) {
            ItemCatData catDataParent = new ItemCatData();
            catDataParent.setUrl("/products/" + category.getId() + ".html");
            catDataParent.setName("<a href='" + catDataParent.getUrl() + "'>" + category.getName() + "</a>");
            result.getItemCats().add(catDataParent);
            if (!category.getIsParent())
                continue;
            Long parentId = category.getId();
            List<ItemCategory> itemCatChildren = map.get(parentId);
            List<ItemCatData> catDataChildren = new ArrayList<>();
            catDataParent.setItems(catDataChildren);

            for (ItemCategory itemCat : itemCatChildren) {
                ItemCatData catDataChild = new ItemCatData();
                catDataChild.setName(itemCat.getName());
                catDataChild.setUrl("/produts/" + itemCat.getId() + ".html");
                catDataChildren.add(catDataChild);
                if (itemCat.getIsParent()) {
                    //继续封装
                    List<ItemCategory> cats = map.get(itemCat.getId());
                    List<String> catDataThrith = new ArrayList<>();
                    catDataChild.setItems(catDataThrith);
                    for (ItemCategory catThird : cats) {
                        catDataThrith.add("/products/" + catThird.getId() + ".html|" + catThird.getName());
                    }
                }
            }
        }
        //将类目树添加到缓存中
        try {
            this.redisService.set(REDIS_KEY_ITEMCAT, MAPPER.writeValueAsString(result), REDIS_EXPIRE_TIME);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取ID下对应的所有类目
     *
     * @param id
     * @return
     */
    @Override
    public ItemCategoryResult selectAllItemCatById(Long id) {
        ItemCategoryResult categroy = null;
        if (id == 0) {
            categroy = new ItemCategoryResult();
        } else
            categroy = categoryMapper.selectById(id);
        List<ItemCategoryResult> children = categoryMapper.selectChildrenById(id);
        categroy.setChildren(children);
        if (children != null) {
            for (ItemCategoryResult cat : children) {
                cat.setChildren(selectAllItemCatById(cat.getId()).getChildren());
            }
        }
        return categroy;
    }

}
