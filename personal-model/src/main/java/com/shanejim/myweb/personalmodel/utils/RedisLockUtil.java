package com.shanejim.myweb.personalmodel.utils;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-26 16:12
 **/
public class RedisLockUtil {
    private static final String LOCK_SUCCESS = "OK";
    //NX 不存在时才set
    private static final String SET_IF_NOT_EXIST = "NX";
    //PX 过期时间毫秒   EX 秒
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 获取分布式锁
     *
     * @param jedis
     * @param lockKey    锁的key
     * @param requestId  请求标示
     * @param expireTime 过时时间
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {

        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

    /**
     * 释放分布式锁
     *
     * @param jedis     Redis客户端
     * @param lockKey   锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }


}