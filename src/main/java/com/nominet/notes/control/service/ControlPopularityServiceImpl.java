package com.nominet.notes.control.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import com.tide.ss.model.Story;

/**
 * Created by sandeep
 */
public class ControlPopularityServiceImpl implements ControlPopularityService {
	
	private static final Logger LOG = Logger.getLogger("ControlPopularityServiceImpl.class");
	
	//Concurrent map to improve performance in a multithreaded environment
	private Map<Integer, Story> storage = new ConcurrentHashMap<>();
	
	@Override
	public Story getPopularityForId(Integer id) {
		Story story = storage.get(id);
		LOG.info("story retrieved from DB %s for id %s " + story);
		if(story == null){
			story = new Story(id, 0);
		}		
		return story;
	}

	@Override
	public Story setPopularityForId(Integer id, Story story) {
		LOG.info("story retrieved from DB %s for id %s " + story);
		Story s =	storage.put(id, new Story(id, story.getPopularity()));
		return s;
	}

	@Override
	public Story incrementPopularityForId(Integer id) {
		LOG.info("popularity increment for story with id %s " + id);
		Story s = storage.get(id);
		if(s!=null){
			storage.put(id, new Story(id, s.getPopularity()+1));
		} else {
			s= new Story(id, 1);
			LOG.info("story with id %s" + id + "not found");
		}							
		return s;
	}

	@Override
	public Story decrementPopularityForId(Integer id) {
		LOG.info("popularity increment for story with id %s " + id);
		Story s = storage.get(id);
		if(s!=null){
			storage.put(id, new Story(id, s.getPopularity()-1));
		} else {
			s= new Story(id, -1);
			LOG.info("story with id %s" + id + "not found");
		}
		return s;
	}
}
