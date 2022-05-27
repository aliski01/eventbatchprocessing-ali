package com.dcsg.eventBatch.dto;

import java.io.Serializable;

public class Genre implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 6700225709093929055L;
	public int id;
    public String name;
    public String slug;
    public boolean primary;
    public Images images;
    public String image;
    public DocumentSource document_source;
    
    

}
