package com.tide.ss.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Story {
			
	private transient Integer id;
	
	private Integer popularity;	
	
	public Story(Integer id) {
		super();
		this.id = id;
	}
	
	public Story(Integer id, Integer popularity) {
		super();
		this.id = id;
		this.popularity = popularity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Story() {
		super();
	}
	public Integer getPopularity() {
		return this.popularity;
	}

	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}
	
	@Override
	public String toString() {
		return "Story [id =" + id + "popularity=" + popularity  + "]";
	}

}
