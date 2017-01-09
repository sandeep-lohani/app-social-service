package com.tide.ss.domain;

import javax.xml.bind.annotation.XmlRootElement;

import com.tide.ss.model.Story;


/**
 * @author Sandeep
 *
 */
@XmlRootElement
public class ServiceDto {

	private Story story;

	public ServiceDto(Story story) {
		super();
		this.story = story;
	}

	public ServiceDto() {
		super();
	}

	@Override
	public String toString() {
		return "ServiceDto [story=" + story + "]";
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}	
}
