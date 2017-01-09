package com.tide.ss.domain;


/**
 * @author Sandeep
 *
 */
public enum RequestMethod {
	
		GET("GET"), HEAD("HEAD"), POST("POST"), PUT("PUT"), PATCH("PATCH"), DELETE("DELETE"), OPTIONS("OPTIONS"), TRACE("TRACE");

		  private String method;

		  RequestMethod(String method) {
		        this.method = method;
		    }

		    public String method() {
		        return method;
		    }
}
