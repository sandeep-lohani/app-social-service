/**
 * 
 */
package com.tide.ss.control.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.Gson;
import com.tide.ss.Application;
import com.tide.ss.domain.MediaType;
import com.tide.ss.model.Story;

/**
 * @author Sandeep
 *
 */
public class ControlSocialServiceTest {

	private final String USER_AGENT = "Mozilla/5.0";

	@Before
	public void setup() {

	}

	/**
	 * Test method for
	 * {@link com.tide.ss.control.service.ControlPopularityServiceImpl#getPopularity()}
	 * .
	 */
	@Test
	@Ignore
	public void testGetPopularity() throws Exception {
		Application.main(null);
		this.sendGet();
	}

	/**
	 * Test method for
	 * {@link com.tide.ss.control.service.ControlPopularityServiceImpl#getPopularity()}
	 * .
	 */
	@Test
	public void testPostPopularity() throws Exception {
		Application.main(null);
		this.sendGet();
		this.sendPost();
		this.sendGet();
		this.sendPut(1);
		this.sendPut(1);
		this.sendPut(-1);
		this.sendGet();
		//this.sendHead();
	}

	// HTTP PUT request
	private void sendPut(int i) throws Exception {
		String url = i > 0 ? "http://localhost:8080/story/12/like?t=t"
				: "http://localhost:8080/story/12/dislike?t=t";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("PUT");
		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'PUT' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		// print result
		System.out.println(response.toString());
	}

	// HTTP GET request
	private void sendGet() throws Exception {
		String url = "http://localhost:8080/story/12?t=t";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());
		// Assert.assertTrue(response.toString().contains("popularity\"\\:101"));
	}

	// HTTP HEAD request
	private void sendHead() throws Exception {
		String url = "http://localhost:8080/story/12?t=t";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("HEAD");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'HEAD' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

	}

	// HTTP POST request
	private void sendPost() throws Exception {
		String url = "http://localhost:8080/story/12?t=t";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		Story s = new Story(12, 100);
		Gson gson = new Gson();
		gson.toJson(s);
		String body = gson.toJson(s);

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(body);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post body : " + body);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

	}
}