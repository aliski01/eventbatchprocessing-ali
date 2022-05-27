package com.dcsg.eventBatch.dto;

import java.io.Serializable;

public class Taxonomy implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5272100508929680568L;
	public int id;
	public String name;
	public int parent_id;
	public DocumentSource document_source;
	public int rank;
}
