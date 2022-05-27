package com.dcsg.eventBatch.controller;

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
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;

import com.dcsg.eventBatch.dto.Event;
import com.dcsg.eventBatch.service.EventBatchService;

@ExtendWith(MockitoExtension.class)
class EventBatchJobControllerTest {
	@Mock
	JobLauncher jobLauncher;

	@Mock
	Job processJob;
	@Mock
	EventBatchService service;
	@InjectMocks
	EventBatchJobController batchJobController;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}
	
	@Test
	void testhandle() {
		String invokeStatus=null;
		try {
			invokeStatus = batchJobController.handle();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(invokeStatus);
	}
	
	@Test
	void testGetEventsList() {
		Map<String, Event> eventList =batchJobController.getEventsList();
		assertNotNull(eventList);
	}

	@Test
	void testFindById_validId() {
		Event event = new Event();
		Mockito.when(service.findById(ArgumentMatchers.anyString())).thenReturn(event);
		ResponseEntity<Event> response = batchJobController.findById("5379804");
		assertNotNull(response);
		assertEquals(200, response.getStatusCodeValue());
		
	}
	@Test
	void testFindById_invalidNumber() {
		ResponseEntity<Event> response = batchJobController.findById("5379asd4");
		assertNotNull(response);
		assertEquals(400, response.getStatusCodeValue());
		
	}
	@Test
	void testFindById_InvalidEvent() {
		ResponseEntity<Event> response = batchJobController.findById("123");
		assertNotNull(response);
		assertEquals(404, response.getStatusCodeValue());
		
	}

}
