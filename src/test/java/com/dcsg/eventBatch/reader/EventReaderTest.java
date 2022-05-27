package com.dcsg.eventBatch.reader;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.dcsg.eventBatch.dto.Event;
import com.dcsg.eventBatch.dto.Meta;
import com.dcsg.eventBatch.dto.Root;
@ExtendWith(MockitoExtension.class)
class EventReaderTest {
	private static final Logger log = LoggerFactory.getLogger(EventReaderTest.class);
	@Mock
	RestTemplate restTemplate;
	@Mock
	Root eventData;
	@Mock
	Meta meta;
	@Mock
	UriComponentsBuilder builder;
	@InjectMocks
	EventReader reader;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testRead() {
		
		  //String testUri = "https://api.seatgeek.com/2/events";
		  //when(builder.toUriString()).thenReturn(testUri);
		Root eventData = new Root();
		Event event1 = new Event();
		event1.setId("1");
		event1.setShort_title("Event1");
		Event event2 = new Event();
		EventSetter.seEventDefault(event2);
		log.debug("Eventdetails:  "+event2);
		Event event3 = new Event(null, "3", null, null, false, null, false, null, null, false, null, null, null, null, null, 0, null, null, false, null, 0, null, null, eventData, event1, null, false, event2, null, null);
		ArrayList<Event> events = new ArrayList<>();
		events.add(event1);
		events.add(event2);
		events.add(event3);
		eventData.setEvents(events);
		meta.page=1;
		meta.total=3;
		meta.per_page=3;
		eventData.setMeta(meta);
		
		//eventData.getMeta().total=2;
		//when(eventData.getMeta().total).thenReturn(2);
		ResponseEntity<Root> responseEntity = new ResponseEntity<Root>(eventData,HttpStatus.OK);
		
		//log.debug(responseEntity.getBody());
		when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.<HttpEntity<?>>any(), ArgumentMatchers.<Class<Root>>any()))
		.thenReturn(responseEntity);
		try {
			assertNotNull(reader.read());
		} catch (UnexpectedInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NonTransientResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testSetUriBuilder() {
		//EventReader reader = new EventReader();
		String uriString = reader.setUriBuilder(1, 20);
		//log.debug(uriString);
		assertNotNull(uriString);
	}

	@Test
	void testFetchEventDataFromRestApi() {
		//EventReader reader = new EventReader();
		//Root event = new Root();
		ResponseEntity<Root> responseEntity = new ResponseEntity<Root>(eventData,HttpStatus.OK);
		
		//log.debug(responseEntity.getBody());
		when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.<HttpEntity<?>>any(), ArgumentMatchers.<Class<Root>>any()))
		.thenReturn(responseEntity);
		eventData =reader.fetchEventDataFromRestApi("https://api.test.com");
		assertNotNull(eventData);
	}

}
