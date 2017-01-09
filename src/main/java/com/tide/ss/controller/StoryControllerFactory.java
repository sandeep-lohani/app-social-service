package com.tide.ss.controller;


/**
 * Created by sandeep
 */
public class StoryControllerFactory {
	private static StoryController storyController = new StoryController();
	
	public static StoryController getStoryController(){
		return storyController;
	}
}
