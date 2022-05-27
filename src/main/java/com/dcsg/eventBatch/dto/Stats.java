package com.dcsg.eventBatch.dto;
import java.io.Serializable;
import java.util.ArrayList;

public class Stats implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -821032114176095553L;
	public int event_count;
    public int listing_count;
    public int average_price;
    public int lowest_price_good_deals;
    public int lowest_price;
    public int highest_price;
    public int visible_listing_count;
    public ArrayList<Integer> dq_bucket_counts;
    public int median_price;
    public int lowest_sg_base_price;
    public int lowest_sg_base_price_good_deals;
}
