package com.dcsg.eventBatch.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import com.dcsg.eventBatch.dto.Event;

@ExtendWith(MockitoExtension.class)
class EventRepositoryImplTest {
	@Mock
	RedisTemplate<String, Event> redisTemplate;
	@InjectMocks
	EventRepositoryImpl repositoryImpl;
	HashOperations<String, String, Event> hashOperations;

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
		event.setId("543202");
		repositoryImpl.save(event);
	}

	@Test
	void testFindAll() {

		// hashOperations=redisTemplate.opsForHash();
		Map<String, Event> eventMap = new HashMap<>();
		Event event = new Event();
		eventMap.put("1", event);
		Mockito.when(hashOperations.entries(ArgumentMatchers.anyString())).thenReturn(eventMap);
		Map<String, Event> eventList1 = repositoryImpl.findAll();
		assertNotNull(eventList1);

	}

	@Test
	void testFindById() {

		Event event = new Event();
		event.setId("123");
		Mockito.doReturn(event).when(hashOperations).get(Mockito.any(), Mockito.any());
		// Mockito.when(hashOperations.get(ArgumentMatchers.any(),
		// ArgumentMatchers.any())).thenReturn(event);
		Event response = repositoryImpl.findById("123");
		assertNotNull(response);
	}

}
