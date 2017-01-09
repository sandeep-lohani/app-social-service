package com.tide.ss.domain;

import java.util.ArrayList;
import java.util.List;

public enum Path {	
	
	GET("/story/[0-9]+"),
	POST("/story/[0-9]+"),
	PUT("/story/[0-9]+/like;/story/[0-9]+/dislike");

	  private String value;

	  Path(String value) {
	        this.value = value;
	    }
	  
	  public List<String> getPathList(){
		  List<String> pathList = new ArrayList<>();
		  if(value.contains(";")){
			  for(String s:value.split(";")){
				  pathList.add(s);
			  }				  
		  } else
			  pathList.add(value);
		  return pathList;
	  }
}
