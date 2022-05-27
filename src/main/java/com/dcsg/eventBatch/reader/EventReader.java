package com.dcsg.eventBatch.reader;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.dcsg.eventBatch.dto.Event;
import com.dcsg.eventBatch.dto.Root;

@Component
public class EventReader implements ItemReader<Event> {

	private static final Logger log = LoggerFactory.getLogger(EventReader.class);

	RestTemplate restTemplate = new RestTemplate();
	
	/* @Value("${chunk_size}") private int chunkSize; */
	 

	@Autowired
	private HttpEntity<String> entity;

	private int nextEventIndex =0;   
	ArrayList<Event> eventList = new ArrayList<Event>();
	int total = 0;
	int page = 0;
	int perPage = 10;
	Root eventData = new Root();	

	@Override
	public Event read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// log.info("Reading the information of the next Event");
		
		if(nextEventIndex==0)
		{
			page++;	
			log.debug("page: " +page);
			String builder = setUriBuilder(page, perPage);
			eventData = fetchEventDataFromRestApi(builder);
			eventList =eventData.getEvents();
			log.debug("eventList Size: " + eventList.size());
			/*
			 * for(Event e : eventList) { System.out.println(e.id); }
			 */	
		}

		if(nextEventIndex >= eventList.size()) 
		{
			total = /* eventData.getMeta().total */ 20 - (page * perPage);			
			nextEventIndex = 0;

			if(total>0) {
				log.debug("Remaining events to fetch: "+total);
				log.info("going to read back!");
				read();	
			}
			else {
				log.info("Reading Complete----------------");
				return null;
			}

		}

		Event nextEvent = eventList.get(nextEventIndex);
		log.debug("eventIndex: "+ nextEventIndex +" EventId:: "+eventList.get(nextEventIndex).getId());
		nextEventIndex++;
		return nextEvent;
		
	}

	public String setUriBuilder(int page, int perPage) {
		String baseUrl = "https://api.seatgeek.com/2/events";
		String clientId = "MjU4Mzc5MzJ8MTY0NTY5MTEwOC42NTQxODA4";
		String clientSecret = "75e407df985dad4c85127f6d3763e7ccf0e981125b9a699f60ee41f34125c4ba";

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl);

		builder.queryParam("per_page", perPage).queryParam("page", page).queryParam("client_id", clientId)
		.queryParam("client_secret", clientSecret);
		log.info(builder.toUriString());
		return builder.toUriString();
	}

	public Root fetchEventDataFromRestApi(String builder) {

		ResponseEntity<Root> response = restTemplate.exchange(builder, HttpMethod.GET, entity, Root.class);
		log.info("fetching..");
		return response.getBody();
	}

}
