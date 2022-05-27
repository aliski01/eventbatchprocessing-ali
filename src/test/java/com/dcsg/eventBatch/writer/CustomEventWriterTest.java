package com.dcsg.eventBatch.writer;

import static org.mockito.Mockito.verify;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dcsg.eventBatch.dto.Event;
import com.dcsg.eventBatch.repository.EventRepositoryImpl;

@ExtendWith(MockitoExtension.class)
class CustomEventWriterTest {
	
	@Mock
	EventRepositoryImpl eventRepositoryImpl;
	@Spy
	@InjectMocks
	CustomEventWriter writer;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testWrite() {
		Event event1 = new Event();
		Event event2 = new Event();
		ArrayList<Event> events = new ArrayList<>();
		events.add(event1);
		events.add(event2);
		//when(eventRepositoryImpl.save(ArgumentMatchers.any())).thenReturn();
		try {
			writer.write(events);
			verify(writer).write(events);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
