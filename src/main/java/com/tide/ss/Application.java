package com.tide.ss;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

import com.sun.net.httpserver.HttpServer;
import com.tide.ss.handler.StoryHandler;


/**
 * Created by sandeep
 */
public class Application {
	
	private static final Logger log = Logger.getLogger("Application.class");

    /**
     * The entry point, starts the application.
     *
     * @param args
     */
    public static void main(String[] args) {
    	log.info("Social service app starting");
    	try {
    		HttpServer server = HttpServer.create(new InetSocketAddress(InetAddress.getLoopbackAddress(), 8080),0);
			StoryHandler handler = new StoryHandler();
			server.createContext("/", handler);
	    	server.start();	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
        log.info("Social service app started");
    }
    
    
}
