package com.dcsg.eventBatch.dto;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Meta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7836890467402076226L;
	public int total;
	public int took;
	public int page;
	public int per_page;
	public Object geolocation;
}
