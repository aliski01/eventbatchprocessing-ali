package com.dcsg.eventBatch.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcsg.eventBatch.dto.Event;
import com.dcsg.eventBatch.repository.EventRepositoryImpl;

@Service
public class EventBatchService {
	
	@Autowired
	EventRepositoryImpl repositoryImpl;

	public Map<String, Event> findAll() {
		
		return repositoryImpl.findAll();
	}

	public Event findById(String id) {
		
		return repositoryImpl.findById(id);
	}
		

}
