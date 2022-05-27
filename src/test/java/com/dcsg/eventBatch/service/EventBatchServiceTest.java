package com.dcsg.eventBatch.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.dcsg.eventBatch.dto.Event;
import com.dcsg.eventBatch.repository.EventRepositoryImpl;

@ExtendWith(MockitoExtension.class)
class EventBatchServiceTest {
	@Mock
	EventRepositoryImpl repositoryImpl;
	@InjectMocks
	EventBatchService batchService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testFindAll() {
		Map<String, Event> eventList = batchService.findAll();
		assertNotNull(eventList);
	}

	@Test
	void testFindById() {
		Event event = new Event();
		Mockito.when(repositoryImpl.findById(ArgumentMatchers.anyString())).thenReturn(event);
		Event response = batchService.findById("5379804");
		assertNotNull(response);
	}

}
