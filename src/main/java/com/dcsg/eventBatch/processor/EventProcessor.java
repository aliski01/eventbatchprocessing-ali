package com.dcsg.eventBatch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.dcsg.eventBatch.dto.Event;



public class EventProcessor implements ItemProcessor<Event, Event> {

	@Override
	public Event process(Event data) throws Exception {
		return data;
	}

}
