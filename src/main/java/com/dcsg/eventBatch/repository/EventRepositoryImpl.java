package com.dcsg.eventBatch.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Repository;

import com.dcsg.eventBatch.dto.Event;

@Repository
public class EventRepositoryImpl implements EventRepository {

	private static final Logger log = LoggerFactory.getLogger(EventRepositoryImpl.class);
	@Autowired
	public RedisTemplate<String, Event> redisTemplate;
	public static final String HASH_KEY = "EVENT1";
	public HashOperations<String, String, Event> hashOperations;//to access redis cache

	public EventRepositoryImpl(RedisTemplate<String, Event> redisTemplate) {
		this.redisTemplate = redisTemplate;
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public void save(Event event) {

		hashOperations.put(HASH_KEY, event.getId(), event);
	}

	@Override
	@Cacheable(value="HASH_KEY")
	public Map<String, Event> findAll() {
		return hashOperations.entries(HASH_KEY);
	}

	@Override
	@Cacheable(key="#id", value="HASH_KEY")
	public Event findById(String id) {
		//System.out.println("findById"+id);
		return  hashOperations.get(HASH_KEY, id);
	}
	/*
	 * @Override public void update(Event event) { save(event); }
	 * 
	 * @Override public void delete(String id) { hashOperations.delete(HASH_KEY,
	 * id); }
	 */

	

}
