package com.redis.guide.springbootredis.config;

import com.redis.guide.springbootredis.model.Coder;
import io.netty.util.internal.StringUtil;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.PutMapping;

@Configuration
public class SpringConfig {

    @Value("${redis.host}")
    private String host;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.port}")
    private Integer port;

    @Value("${redis.jedis.pool.max-total}")
    private Integer maxTotal;

    @Value("${redis.jedis.pool.min-idle}")
    private Integer minIdle;

    @Value("${redis.jedis.pool.min-idle}")
    private Integer maxIdle;

    @Bean
    public JedisClientConfiguration getJedisClientConfiguration(){
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder JedisPoolingClientConfigurationBuilder=(
                JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        GenericObjectPoolConfig genericObjectPoolConfig=new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxTotal(maxTotal);
        genericObjectPoolConfig.setMaxIdle(maxIdle);
        genericObjectPoolConfig.setMinIdle(minIdle);

        return JedisPoolingClientConfigurationBuilder.poolConfig(genericObjectPoolConfig).build();
    }

    @Bean
    public JedisConnectionFactory getJedisConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration=new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        if(!StringUtil.isNullOrEmpty(password)){
            redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        }
        redisStandaloneConfiguration.setPort(port);
        return new JedisConnectionFactory(redisStandaloneConfiguration,getJedisClientConfiguration());
    }

    @Bean
    public RedisTemplate redisTemplate(){
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(getJedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public ListOperations<String, Coder> listOperations(RedisTemplate<String,Coder> redisTemplate){
        return redisTemplate.opsForList();
    }

    @Bean
    public SetOperations<String,Coder> setOperations(RedisTemplate<String ,Coder> redisTemplate){
        return redisTemplate.opsForSet();
    }

    @Bean
    public HashOperations<String,Integer,Coder> hashOperations(RedisTemplate<String, Order> redisTemplate){
        return redisTemplate.opsForHash();
    }

}
