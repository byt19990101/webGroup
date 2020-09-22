package com.zzxx.bookstore.service.impl;

import com.zzxx.bookstore.dao.CategoryDao;
import com.zzxx.bookstore.dao.impl.CategoryDaoImpl;
import com.zzxx.bookstore.domain.Category;
import com.zzxx.bookstore.domain.Type;
import com.zzxx.bookstore.service.CategoryService;
import com.zzxx.bookstore.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Type> findTname() {
        Jedis jedis = JedisUtil.getJedis();
        // 1.从redis缓存中查询数据
        Set<Tuple> types = jedis.zrangeWithScores("type",0,-1);
        if(types == null || types.size() == 0){
            // 1.2查不到数据，从数据库中查询并返回
            List<Type> list = categoryDao.findTname();
            // 2.第一次从数据库中查询出来的数据，存储到redis缓存中
            for(Type type:list){
                jedis.zadd("type",type.getTid(),type.getTname());
            }
            return list;
        } else{
            // 1.1查到数据，直接将数据封装返回
            List<Type> typies = new ArrayList<>();
            for(Tuple tuple:types){
                Type type = new Type();
                type.setTid((int)tuple.getScore());
                type.setTname(tuple.getElement());
                typies.add(type);
            }
            return typies;
        }
    }

    @Override
    public List<Category> findAll() {
        return null;
    }
}
