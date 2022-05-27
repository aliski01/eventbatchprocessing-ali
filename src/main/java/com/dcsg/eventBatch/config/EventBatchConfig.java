package com.dcsg.eventBatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import com.dcsg.eventBatch.dto.Event;
import com.dcsg.eventBatch.listener.JobCompletionListener;
import com.dcsg.eventBatch.processor.EventProcessor;
import com.dcsg.eventBatch.reader.EventReader;
import com.dcsg.eventBatch.writer.CustomEventWriter;

@Configuration
@EnableRedisRepositories
public class EventBatchConfig {
	@Value("${chunk_size}")
	private int chunkSize;
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	CustomEventWriter customEventWriter;
	
	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("processJob")
				.incrementer(new RunIdIncrementer()).listener(listener())
				.flow(orderStep1()).end().build();
	}

	@Bean
	public Step orderStep1() {
		return stepBuilderFactory.get("orderStep1").<Event, Event> chunk(chunkSize)
				.reader(new EventReader()).processor(new EventProcessor())
				.writer(customEventWriter).build();
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionListener();
	}
	
	 /*Below code is used for writing to CSV file*/
	
	 /*	  
	 * 
	 * private Resource outputResource = new
	 * FileSystemResource("target/eventList.csv");
	 * 
	 * @Bean public FlatFileItemWriter<Event> eventWriter() { //Create writer
	 * instance FlatFileItemWriter<Event> writer = new FlatFileItemWriter<>();
	 * 
	 * //Set output file location writer.setResource(outputResource);
	 * 
	 * //All job repetitions should "append" to same output file
	 * writer.setAppendAllowed(true);
	 * 
	 * //Name field values sequence based on object properties
	 * writer.setLineAggregator(new DelimitedLineAggregator<Event>() { {
	 * setDelimiter(","); setFieldExtractor(new BeanWrapperFieldExtractor<Event>() {
	 * { setNames(new String[] { "id", "type", "short_title" }); } }); } }); return
	 * writer; }
	 */
}
