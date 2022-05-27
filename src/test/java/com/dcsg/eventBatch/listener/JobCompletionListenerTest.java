package com.dcsg.eventBatch.listener;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
@ExtendWith(MockitoExtension.class)
class JobCompletionListenerTest {
	@Mock
	JobExecution jobExecution;
	@Spy
	@InjectMocks
	JobCompletionListener completionListener;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testAfterJobJobExecution() {
		Mockito.when(jobExecution.getStatus()).thenReturn(BatchStatus.COMPLETED);
		
		completionListener.afterJob(jobExecution); 
		verify(completionListener).afterJob(jobExecution);
	}

}
