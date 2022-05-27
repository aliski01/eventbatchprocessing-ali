package com.dcsg.eventBatch;

import java.util.Arrays;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.dcsg.eventBatch.dto.Event;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableBatchProcessing
@EnableCaching
public class EventBatchProcessingApplication {

	@Bean
	public HttpEntity<String> getEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		return new HttpEntity<String>(headers);
	}

	public static void main(String[] args) {
		SpringApplication.run(EventBatchProcessingApplication.class, args);
	}
	
	@Bean
	RedisTemplate<String, Event> redisTemplate() {
		RedisTemplate<String, Event> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		
		  redisTemplate.setKeySerializer(new StringRedisSerializer());
		  redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		  redisTemplate.setHashKeySerializer(new JdkSerializationRedisSerializer());
		  redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		  redisTemplate.setEnableTransactionSupport(true);
		  redisTemplate.afterPropertiesSet();
		 
		return redisTemplate;
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName("localhost");
		redisStandaloneConfiguration.setPort(6379);
		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {	
		return new RestTemplate();
	}

}
