package com.tide.ss.domain;

public enum Mapping {	

	STORY("/story/"),LIKE("/like"), DISLIKE("/dislike"), VERSION("/v1");

	  private String value;

	  Mapping(String value) {
	        this.value = value;
	    }

	    public String value() {
	        return value;
	    }

}
