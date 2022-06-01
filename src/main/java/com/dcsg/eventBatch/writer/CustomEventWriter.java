package com.dcsg.eventBatch.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.dcsg.eventBatch.dto.Event;
import com.dcsg.eventBatch.repository.EventRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class CustomEventWriter implements ItemWriter<Event> {
	private static final Logger log = LoggerFactory.getLogger(CustomEventWriter.class);
	
	@Autowired	
	EventRepositoryImpl eventRepositoryImpl;
	@Autowired
	ObjectMapper mapper;
	@Autowired
	public RedisTemplate<String, String> redisTemplate;
	public HashOperations<String, String, String> hashOperations;
	public static final String HASH_KEY = "EVENTTest";
	
	public CustomEventWriter(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
		hashOperations = redisTemplate.opsForHash();
	}
	
	@Override
	public void write(List<? extends Event> events) throws Exception {
		
			for (Event event : events) {
				String eventId=String.valueOf(event.getId());
				String jsonInString = mapper.writeValueAsString(event);
				
				hashOperations.put(HASH_KEY,eventId,jsonInString);
			}
		
	}

}
