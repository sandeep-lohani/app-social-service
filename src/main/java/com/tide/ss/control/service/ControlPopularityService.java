package com.tide.ss.control.service;

import com.tide.ss.model.Story;
/**
 * Created by sandeep
 */
public interface ControlPopularityService {
	
	Story getPopularityForId(Integer id);
		
	Story setPopularityForId(Integer id, Story story);
	
	Story incrementPopularityForId(Integer id);
	
	Story decrementPopularityForId(Integer id);
}
