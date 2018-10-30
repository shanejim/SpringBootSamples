package com.shanejim.myweb.personaladmin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-30 16:29
 **/
@Configuration
@EnableCaching
@EnableConfigurationProperties({RedisProperies.class})
public class RedisCacheConfiguration extends CachingConfigurerSupport {

    Logger logger = LoggerFactory.getLogger(RedisCacheConfiguration.class);

    @Autowired
    private RedisProperies redisProperies;

    @Bean
    public JedisPool redisPoolFactory() {
        logger.info("注入jedis连接池！！！");

        JedisPool jedisPool = new JedisPool(redisProperies, redisProperies.getHost(),
                redisProperies.getPort(), redisProperies.getConnectionTimeout(),
                redisProperies.getSoTimeout(), redisProperies.getPassword(),
                redisProperies.getDatabase(), redisProperies.getClientName(),
                redisProperies.isSsl(), redisProperies.getSslSocketFactory(),
                redisProperies.getSslParameters(), redisProperies.getHostnameVerifier());

        return jedisPool;
    }

}
