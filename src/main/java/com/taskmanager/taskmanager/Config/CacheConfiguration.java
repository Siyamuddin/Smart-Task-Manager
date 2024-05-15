//package com.taskmanager.taskmanager.Config;
//
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericToStringSerializer;
//import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
//
//@Configuration
//@EnableCaching
//public class CacheConfiguration {
//    @Bean
//    public LettuceConnectionFactory connectionFactory()
//    {
//        RedisProperties properties=redisProperties();
//        RedisStandaloneConfiguration redisStandaloneConfiguration=new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setHostName(redisProperties().getHost());
//        redisStandaloneConfiguration.setPort(redisProperties().getPort());
//        return new LettuceConnectionFactory(redisStandaloneConfiguration);
//    }
//
//    @Bean
//    public RedisTemplate<String,Object> template()
//    {
//        RedisTemplate<String,Object> template=new RedisTemplate<>();
//        template.setConnectionFactory(connectionFactory());
//        template.setKeySerializer(new JdkSerializationRedisSerializer());
//        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
//        return template;
//    }
//
//
//
//    @Bean
//    @Primary
//   public RedisProperties redisProperties()
//   {
//       return new RedisProperties();
//   }
//
//}
