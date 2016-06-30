package com.onlineshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.Map;

/**
 * Redis的服务类
 * Created by YU on 2016/6/30.
 */
@Service
public class RedisService {
    @Autowired
    private ShardedJedisPool shardedJedisPool;

    private <T> T execute(Function<ShardedJedis, T> fun) {
        ShardedJedis shardedJedis = null;
        //从连接池中获取到jedis分片
        shardedJedis = shardedJedisPool.getResource();
        return fun.callBack(shardedJedis);
    }

    /**
     * String set
     *
     * @param key
     * @param value
     * @return
     */
    public String set(final String key, final String value) {
        return this.execute(new Function<ShardedJedis, String>() {
            @Override
            public String callBack(ShardedJedis shardedJedis) {
                return shardedJedis.set(key, value);
            }
        });
    }

    /**
     * String  expire set
     *
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public String set(final String key, final String value, final Integer seconds) {
        return this.execute(new Function<ShardedJedis, String>() {
            @Override
            public String callBack(ShardedJedis shardedJedis) {
                String str = shardedJedis.set(key, value);
                shardedJedis.expire(key, seconds);
                return str;
            }
        });
    }

    /**
     * 删除
     *
     * @param key
     * @return
     */
    public Long del(final String key) {
        return this.execute(new Function<ShardedJedis, Long>() {
            @Override
            public Long callBack(ShardedJedis shardedJedis) {
                return shardedJedis.del(key);
            }
        });
    }

    /**
     * 设置有效期
     *
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(final String key, final Integer seconds) {
        return this.execute(new Function<ShardedJedis, Long>() {
            @Override
            public Long callBack(ShardedJedis shardedJedis) {
                return shardedJedis.expire(key, seconds);
            }
        });
    }

    /**
     * Hash 设置值
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hset(final String key, final String field, final String value) {
        return this.execute(new Function<ShardedJedis, Long>() {
            @Override
            public Long callBack(ShardedJedis shardedJedis) {
                return shardedJedis.hset(key, field, value);
            }
        });
    }

    /**
     * Hash 设置key 对应有效时间
     * @param key
     * @param field
     * @param value
     * @param seconds
     * @return
     */
    public Long hset(final String key, final String field, final String value, final Integer seconds) {
        return this.execute(new Function<ShardedJedis, Long>() {
            @Override
            public Long callBack(ShardedJedis shardedJedis) {
                shardedJedis.hset(key, field, value);
                return shardedJedis.expire(key, seconds);
            }
        });
    }

    /**
     * Hash 获取
     * @param key
     * @param field
     * @return
     */
    public String hget(final String key, final String field) {
        return this.execute(new Function<ShardedJedis, String>() {
            @Override
            public String callBack(ShardedJedis shardedJedis) {
                return shardedJedis.hget(key, field);
            }
        });
    }

    /**
     * Hash 获取所有field-value
     * @param key
     * @return
     */
    public Map<String, String> hgetAll(final String key){
        return this.execute(new Function<ShardedJedis, Map<String, String>>() {
            @Override
            public Map<String, String> callBack(ShardedJedis shardedJedis) {
                return shardedJedis.hgetAll(key);
            }
        });
    }

    /**
     * Hash 删除
     * @param key
     * @param field
     * @return
     */
    public Long hdel(final String key, final String field){
        return this.execute(new Function<ShardedJedis, Long>() {
            @Override
            public Long callBack(ShardedJedis shardedJedis) {
                return shardedJedis.hdel(key,field);
            }
        });
    }
}
