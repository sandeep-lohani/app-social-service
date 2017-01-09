package com.tide.ss.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.xml.internal.ws.api.server.HttpEndpoint;
import com.tide.ss.controller.StoryController;
import com.tide.ss.controller.StoryControllerFactory;
import com.tide.ss.domain.Mapping;
import com.tide.ss.domain.MediaType;
import com.tide.ss.domain.Path;
import com.tide.ss.model.Story;

/**
 * Created by sandeep
 */
public class StoryHandler implements HttpHandler {

	private final StoryController storyController = StoryControllerFactory
			.getStoryController();

	@Override
	public void handle(HttpExchange t) throws IOException {
		System.out.println("in min");
		URI uri = t.getRequestURI();
		String path = uri.getPath();
		boolean match = false;
		switch (t.getRequestMethod()) {

		case "GET":
			for (String url : Path.GET.getPathList()) {
				if (path.matches(url)) {
					match = true;
					handleGetRequest(t, path);
					break;
				}
			}
			if (!match)
				handleDefault(t);
			break;
		case "POST":
			for (String url : Path.POST.getPathList()) {
				if (path.matches(url)) {
					match = true;
					handlePostRequest(t, path);
					break;
				}
			}
			if (!match)
				handleDefault(t);
			break;
		case "PUT":
			for (String url : Path.PUT.getPathList()) {
				if (path.matches(url)) {
					match = true;
					handlePutRequest(t, path);
					break;
				}
			}
			if (!match)
				handleDefault(t);
			break;
		default:
			handleDefault(t);
		}
	}

	private void handleDefault(HttpExchange t) {
		// send response
		String response = "Not Found";
		Headers h = t.getResponseHeaders();
		try {
			t.sendResponseHeaders(404, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void handlePutRequest(HttpExchange t, String path) {
		Story story = null;
		int startIndex = path.indexOf(Mapping.STORY.value()) + 7;
		if (path.contains(Mapping.LIKE.value())) {
			int endIndex = path.indexOf(Mapping.LIKE.value());
			story = storyController.incrementPopularity(path.substring(
					startIndex, endIndex));
		} else if (path.contains(Mapping.DISLIKE.value())) {
			int endIndex = path.indexOf(Mapping.DISLIKE.value());
			story = storyController.decrementPopularity(path.substring(
					startIndex, endIndex));
		} else {
			handleDefault(t);
			return;
		}

		Gson gson = new Gson();
		// send response
		String response = "Path: " + path + "\n";
		response = gson.toJson(story);
		Headers h = t.getResponseHeaders();
		h.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		try {
			t.sendResponseHeaders(200, response.length());

			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handlePostRequest(HttpExchange t, String path) {
		InputStream is = t.getRequestBody();
		Story story = null;
		try {
			Gson gson = new Gson();
			if (is != null) {
				final BufferedReader reader = new BufferedReader(
						new InputStreamReader(is));
				story = gson.fromJson(reader, Story.class);
				System.out.println("story is :" + story);
			}
			is.close();

			story = storyController.setPopularity(
					path.substring(path.indexOf(Mapping.STORY.value()) + 7),
					story);

			// send response
			String response = "Path: " + path + "\n";
			response = gson.toJson(story);
			Headers h = t.getResponseHeaders();
			h.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
			// h.set("Content-Type", "text/html; charset=iso-8859-1");
			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handleGetRequest(HttpExchange t, String path) {
		Story story = storyController.getPopularity(path.substring(path
				.indexOf(Mapping.STORY.value()) + 7));
		Gson gson = new Gson();

		// send response
		String response = "Path: " + path + "\n";
		response = gson.toJson(story);
		Headers h = t.getResponseHeaders();
		h.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		// h.set("Content-Type", "text/html; charset=iso-8859-1");
		try {
			t.sendResponseHeaders(200, response.length());

			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
