package com.tide.ss.controller;

import java.util.logging.Logger;

import com.tide.ss.control.service.ControlPopularityService;
import com.tide.ss.control.service.ControlPopularityServiceFactory;
import com.tide.ss.model.Story;

/**
 * Created by sandeep
 */
//@Path("/story")
public class StoryController {
	
    private static final Logger LOG = Logger.getLogger("StoryController.class");
    
    private final ControlPopularityService controlPopService = ControlPopularityServiceFactory.getControlPopularityService();

    //GET /story/{id} - Retrieve the current popularity of a story
    public Story getPopularity(String id) {
        LOG.info("get story for id : " + id);
        return controlPopService.getPopularityForId(Integer.valueOf(id));
    }


    // POST /story/{id} - Explicitly set the current popularity of a story
    public Story setPopularity(String id, Story story) {
        LOG.info("save popularity for a story");
        return controlPopService.setPopularityForId(Integer.valueOf(id), story);
    }

    /* PUT /story/{id}/like - “Like” the story (ie. increment the story’s popularity by
    one). A story that has not yet been seen can be assumed to have a popularity of 0.*/
    public Story incrementPopularity(String id) {
        LOG.info("increment popularity by 1 for story id : "+ id);
        return controlPopService.incrementPopularityForId(Integer.valueOf(id));
    }

    /* PUT /story/{id}/dislike - “Dislike” the story (ie. decrement the story’s popularity
    by one).   
    */    
    public Story decrementPopularity(String id) {
    	LOG.info("decrement popularity by 1 for story id : "+ id);
        return controlPopService.decrementPopularityForId(Integer.valueOf(id));
    }
}
