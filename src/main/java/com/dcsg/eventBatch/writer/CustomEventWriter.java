package com.dcsg.eventBatch.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcsg.eventBatch.dto.Event;
import com.dcsg.eventBatch.repository.EventRepositoryImpl;
@Service
public class CustomEventWriter implements ItemWriter<Event> {
	private static final Logger log = LoggerFactory.getLogger(CustomEventWriter.class);
	
	@Autowired	
	EventRepositoryImpl eventRepositoryImpl;
	//@Autowired
	//RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public void write(List<? extends Event> events) throws Exception {
		for (Event event : events) {
			eventRepositoryImpl.save(event);
			log.debug("Writing the Event data, Id:: " + event.getId() +"   Short Title ::  " + event.getShort_title());
			
		}
	}

}
