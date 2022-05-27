package com.dcsg.eventBatch.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class Venue implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4412278394229237804L;
	public String state;
    public String name_v2;
    public String postal_code;
    public String name;
    public ArrayList<Object> links;
    public String timezone;
    public String url;
    public double score;
    public Location location;
    public String address;
    public String country;
    public boolean has_upcoming_events;
    public int num_upcoming_events;
    public String city;
    public String slug;
    public String extended_address;
    public int id;
    public int popularity;
    public Object access_method;
    public int metro_code;
    public int capacity;
    public String display_location;
}
