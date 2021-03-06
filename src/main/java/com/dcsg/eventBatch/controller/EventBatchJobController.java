package com.dcsg.eventBatch.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dcsg.eventBatch.dto.Event;
import com.dcsg.eventBatch.service.EventBatchService;

@RestController
public class EventBatchJobController {
	Logger log = LoggerFactory.getLogger(EventBatchJobController.class);
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job processJob;
	@Autowired
	EventBatchService service;

	@RequestMapping("/invokejob")
	public String handle() throws Exception {

		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
				.toJobParameters();
		jobLauncher.run(processJob, jobParameters);

		return "Batch job has been invoked";
	}
	
	// Returning all events
	/*
	 * @GetMapping(value = "/template/events") public Map<String, Event>
	 * getEventsList() {
	 * 
	 * return service.findAll(); }
	 */
	// Returning paginated events
	@GetMapping(value = "/template/events")//?per_page={pageSize}&page={page}
	public List<Event> getEventsList(@RequestParam(value="per_page",defaultValue = "10") int pageSize, 
			@RequestParam(value="page",defaultValue = "1") int page) {	

		if(!(pageSize>0 && page>0))
		{
			pageSize=10;
			page=1;
		}
		List<Event> eventList = new  ArrayList<>();
		try {
			eventList = service.findAll(page, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Inside controller");
		}
		return eventList;
	}

	// Response for specific Event ID
	@GetMapping("template/events/{id}")
	public ResponseEntity<Event> findById(@PathVariable("id") final String id) {
		Event eventdatabyid = null;
		String eventId = StringUtils.trim(id);
		if(StringUtils.isNumeric(eventId)) {
			eventdatabyid = service.findById(id);
			if(ObjectUtils.isEmpty(eventdatabyid))
				return ResponseEntity.notFound().build();

		} else {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(eventdatabyid);

	}
}
