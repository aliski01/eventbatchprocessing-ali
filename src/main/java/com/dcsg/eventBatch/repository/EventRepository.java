package com.dcsg.eventBatch.repository;

import java.util.Map;

import com.dcsg.eventBatch.dto.Event;

public interface EventRepository {

	void save(Event event);

	Map<String, Event> findAll();

	Event findById(String id);

	/*
	 * void update(Event event);
	 * 
	 * void delete(String id);
	 */

}
