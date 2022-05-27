package com.dcsg.eventBatch.reader;

import java.util.ArrayList;
import java.util.Date;

import com.dcsg.eventBatch.dto.AccessMethod;
import com.dcsg.eventBatch.dto.Announcements;
import com.dcsg.eventBatch.dto.Event;
import com.dcsg.eventBatch.dto.Performer;
import com.dcsg.eventBatch.dto.Stats;
import com.dcsg.eventBatch.dto.Taxonomy;
import com.dcsg.eventBatch.dto.Venue;

public class EventSetter {
	public static Event seEventDefault(Event event)
	{
		Performer performer = new Performer();
		ArrayList<Performer> performers = new ArrayList<>();
		performers.add(performer);
		
		Taxonomy taxonomy = new Taxonomy();
		ArrayList<Taxonomy> taxonomies = new ArrayList<>();
		taxonomies.add(taxonomy);
		
		event.setAccess_method(new AccessMethod());
		event.setAnnounce_date(new Date());
		event.setAnnouncements(new Announcements());
		event.setConditional(false);
		event.setCreated_at(new Date());
		event.setDescription("Sample description");
		event.setDate_tbd(false);
		event.setDatetime_local(new Date());
		event.setDatetime_tbd(false);
		event.setDatetime_utc(new Date());
		event.setDomain_information(null);
		event.setEnddatetime_utc(null);
		event.setEvent_promotion(null);
		event.setId("12345");
		event.setIs_open(true);
		event.setLinks(null);
		event.setPerformers(performers);
		event.setPopularity(0.0);
		event.setScore(0);
		event.setShort_title("Sample short_title");
		event.setStats(new Stats());
		event.setStatus("Sample status");
		event.setTaxonomies(taxonomies);
		event.setThemes(null);
		event.setTime_tbd(false);
		event.setTitle("Sample title");
		event.setType("Sample type");
		event.setUrl("www.sampleurl.com");
		event.setVenue(new Venue());
		event.setVisible_until_utc(null);
		
		return event;
		
		
	}

}
