package com.dcsg.eventBatch.processor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.dcsg.eventBatch.dto.Event;
@ExtendWith(MockitoExtension.class)
class EventProcessorTest {
	@Mock
	Event event;
	@InjectMocks
	EventProcessor processor;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testProcess() {
		Event precessedEvent=null;
		try {
			precessedEvent = processor.process(event);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(precessedEvent);
	}

}
