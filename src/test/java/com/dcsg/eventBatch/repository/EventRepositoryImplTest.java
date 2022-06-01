package com.dcsg.eventBatch.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import com.dcsg.eventBatch.dto.Event;

@ExtendWith(MockitoExtension.class)
class EventRepositoryImplTest {
	@Mock
	RedisTemplate<String, String> redisTemplate;
	@InjectMocks
	EventRepositoryImpl repositoryImpl;
	@Mock
	Cursor<Entry<Object, Object>> cursor;
	HashOperations<String, String, String> hashOperations;

	@SuppressWarnings("unchecked")
	@BeforeEach
	void setUp() throws Exception {
		hashOperations = mock(HashOperations.class);
		repositoryImpl.hashOperations = hashOperations;
		// System.out.println(hashOperations);
	}

	@Test
	void testSave() {
		Event event = new Event();
		event.setId(543202);
		repositoryImpl.save(event);
	}

	@Test
	void testFindAll() {

		// hashOperations=redisTemplate.opsForHash();
		//Map<String, Event> eventMap = new HashMap<>();
		//Cursor<Entry<Object, Object>> cursor ;
		Event event = new Event();
		//eventMap.put("1", event);
		//Mockito.when(cursor.hasNext()).thenReturn(true);
		List<Event> eventList1 = repositoryImpl.findAll(1,10);
		assertNotNull(eventList1);

	}

	@Test
	void testFindById() {

		Event event = new Event();
		event.setId(123);
		Mockito.doReturn(event).when(hashOperations).get(Mockito.any(), Mockito.any());
		// Mockito.when(hashOperations.get(ArgumentMatchers.any(),
		// ArgumentMatchers.any())).thenReturn(event);
		Event response = repositoryImpl.findById("123");
		assertNotNull(response);
	}

}
