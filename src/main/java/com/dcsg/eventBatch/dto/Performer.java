package com.dcsg.eventBatch.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class Performer implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = -5467177544010954615L;
	public String type;
	    public String name;
	    public String image;
	    public int id;
	    public Images images;
	    public Object divisions;
	    public boolean has_upcoming_events;
	    public boolean primary;
	    public Stats stats;
	    public ArrayList<Taxonomy> taxonomies;
	    public String image_attribution;
	    public String url;
	    public double score;
	    public String slug;
	    public int home_venue_id;
	    public String short_name;
	    public int num_upcoming_events;
	    public Object colors;
	    public String image_license;
	    public int popularity;
	    public Location location;
	    public String image_rights_message;
	    public boolean home_team;
	    public boolean away_team;
}
