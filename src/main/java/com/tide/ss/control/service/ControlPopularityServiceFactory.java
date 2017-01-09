package com.tide.ss.control.service;

/**
 * Created by sandeep
 */
public class ControlPopularityServiceFactory {
	private static ControlPopularityService controlPopularityService = new ControlPopularityServiceImpl();
	
	public static ControlPopularityService getControlPopularityService(){
		return controlPopularityService;
	}

	
}
