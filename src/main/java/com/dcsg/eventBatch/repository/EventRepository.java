package com.dcsg.eventBatch.repository;

import java.util.List;
import java.util.Map;

import com.dcsg.eventBatch.dto.Event;

public interface EventRepository {

	void save(Event event);

	List<Event> findAll(int page, int pageSize);

	Event findById(String id);

	/*
	 * void update(Event event);
	 * 
	 * void delete(String id);
	 */

}
