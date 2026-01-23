package org.mdxq.wwjclub.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.Resource;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * 类说明：缓存相关的控制类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/23 15:37
 */
@Configuration
@EnableCaching//开启缓存的全局开关
public class CacheConfig {
    // 缓存的默认过期时间：分钟
    private static final long TTL = 30L;
    // 添加一个Redis的连接工厂
    @Resource
    private RedisConnectionFactory connectionFactory;

    @Bean
    public CacheManager cacheManager() {
        //获取json的核心对象
        ObjectMapper objectMapper = new ObjectMapper();
        /*
        设置jackson在序列化和反序列化过程中，对Java泛型的默认处理方案
        参数1：用于验证泛型类型的对象
        参数2：表示（反）序列化过程中，将泛型类型信息当作属性处理
        参数3：在JSON数据中，将Java泛型类型信息当作属性存储
         */
        objectMapper.activateDefaultTyping(
                objectMapper.getPolymorphicTypeValidator(),
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );
        // 设置LocalDateTime序列化方式
        objectMapper.registerModule(new JavaTimeModule());
        // Jackson有权访问任意对象的任意属性
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

       // 序列化器：避免中文乱码
        Jackson2JsonRedisSerializer serializer
                = new Jackson2JsonRedisSerializer(objectMapper, Object.class);

        // 配置Redis缓存管理器
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(TTL)) // 配置缓存过期时间
                // 指定序列化器
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer))
                .disableCachingNullValues(); // 序列化时过滤null值

         // 返回一个Redis缓存管理器，指定连接工厂和缓存配置器
        return RedisCacheManager.builder(connectionFactory).cacheDefaults(config).build();
    }
}
