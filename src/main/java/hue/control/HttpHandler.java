package hue.control;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class HttpHandler {
    /** Sends a post request. */
	public static void post(String url, String json) {
        // Prints the URL and JSON
		System.out.println("URL: " + url);
		System.out.println("JSON: " + json);

		try {
			// Creates the client and request.
			HttpClient client = HttpClient.newBuilder().build();
			HttpRequest request = HttpRequest.newBuilder(URI.create(url)).POST(BodyPublishers.ofString(json)).build();

			// Sends the request and gets the response.
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			
			// Prints the response
			System.out.println("Response: " + response.body());
		} catch (InterruptedException e) {
			System.out.println("InterruptedException Error: " + e);
		} catch (IOException e) {
			System.out.println("IOException Error: " + e);
		}
	}

    /** Sends a put request. */
    public static void put(String url, String json) {
		// Prints the URL and JSON
		System.out.println("URL: " + url);
		System.out.println("JSON: " + json);

		try {
			// Creates the client and request.
			HttpClient client = HttpClient.newBuilder().build();
			HttpRequest request = HttpRequest.newBuilder(URI.create(url)).PUT(BodyPublishers.ofString(json)).build();

			// Sends the request and gets the response.
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			
			// Prints the response
			System.out.println("Response: " + response.body());
		} catch (InterruptedException e) {
			System.out.println("InterruptedException Error: " + e);
		} catch (IOException e) {
			System.out.println("IOException Error: " + e);
		}
	}

    /** Sends a get request. */
	public static void get(String url) {
		// Prints the URL
		System.out.println("URL: " + url);

		try {
			// Creates the client and request.
			HttpClient client = HttpClient.newBuilder().build();
			HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();

			// Sends the request and gets the response.
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			
			// Prints the response
			System.out.println("Response: " + response.body());
		} catch (InterruptedException e) {
			System.out.println("InterruptedException Error: " + e);
		} catch (IOException e) {
			System.out.println("IOException Error: " + e);
		}
	}

    /** Sends a delete request. */
    public static void delete(String url) {
		// Prints the URL
		System.out.println("URL: " + url);

		try {
			// Creates the client and request.
			HttpClient client = HttpClient.newBuilder().build();
			HttpRequest request = HttpRequest.newBuilder(URI.create(url)).DELETE().build();

			// Sends the request and gets the response.
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			
			// Prints the response
			System.out.println("Response: " + response.body());
		} catch (InterruptedException e) {
			System.out.println("InterruptedException Error: " + e);
		} catch (IOException e) {
			System.out.println("IOExcaption Error: " + e);
		}
	}
}