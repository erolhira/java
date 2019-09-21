package com.jtudy.workshop.java9.newfeatures;

import java.net.Authenticator;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.PasswordAuthentication;
import java.net.ProxySelector;
import java.net.URI;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpClient.Version;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

/*
 * 
 * JEP 110: HTTP 2 Client
 * JEP 110 implements HTTP/2 and WebSocket.
 * This new HTTP client is included as an incubator module, and its goal is to replace the legacy HttpURLConnection API.
 * 
 * Java 9's new incubating HttpClient replacing the HttpURLConnection API.
 * Some other 3rd-party libraries commonly used: Apache HttpClient, Jetty, Spring's RestTemplate.
 * 
 * The HTTP Client module is bundled as an incubator module in JDK 9 and supports HTTP/2 with backward compatibility still facilitating HTTP/1.1.
 * 
 * To use thew new Http2 client, you have o define the module dependency in the module-info.java
 * requires jdk.incubator.httpclient;
 * 
 * The API consists of 3 core classes:
 * HttpRequest – represents the request to be sent via the HttpClient
 * HttpClient – behaves as a container for configuration information common to multiple requests
 * HttpResponse – represents the result of an HttpRequest call
 * 
 * references:
 * http://openjdk.java.net/jeps/110
 * https://www.baeldung.com/java-9-http-client
 * https://www.oracle.com/a/ocom/docs/corporate/java-magazine-jul-aug-2017.pdf
 */
public class Http2Client {
	
	@Test
	public void getHttpRequest() throws Exception {
		
		HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI("http://www.google.com"))
					.version(Version.HTTP_2)
					.headers("name1", "value1", "name2", "value2")
					.timeout(Duration.of(20, ChronoUnit.SECONDS))
					.GET()
					.build();
		
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandler.asString());		
		System.out.println(response.body());			
	}
	
	@Test
	public void postHttpRequest() throws Exception {
		
		HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI("http://www.google.com"))
					.headers("Content-Type", "text/plain;charset=UTF-8")
					.POST(HttpRequest.BodyProcessor.fromString("body here"))
					.build();
		
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandler.asString());		
		System.out.println(response.body());
	}
	
	@Test
	public void settingProxy() throws Exception {
		
		HttpRequest request = HttpRequest.newBuilder(new URI("http://www.google.com")).GET().build();
		HttpResponse<String> response = HttpClient.newBuilder()
			  		.proxy(ProxySelector.getDefault()).build()
				  	.send(request, HttpResponse.BodyHandler.asString());
		System.out.println(response.body());
	}
	
	/*
	 * HttpClient can redirect the request to the new URI automatically if we set the appropriate redirect policy.
	 */
	@Test
	public void settingRedirectPolicy() throws Exception {
		
		HttpRequest request = HttpRequest.newBuilder(new URI("http://www.google.com")).GET().build();
		HttpResponse<String> response = HttpClient.newBuilder()
					.followRedirects(HttpClient.Redirect.ALWAYS).build()
					.send(request, HttpResponse.BodyHandler.asString());
	}
	
	@Test
	public void authentication() throws Exception {

		HttpRequest request = HttpRequest.newBuilder(new URI("http://www.google.com")).GET().build();
		HttpResponse<String> response = HttpClient.newBuilder().authenticator(new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("test_user", "test_password".toCharArray());
			}
		}).build().send(request, HttpResponse.BodyHandler.asString());
	}
	
	@Test
	public void sendAsync() throws Exception {

		HttpRequest request = HttpRequest.newBuilder(new URI("http://www.google.com")).GET().build();
		CompletableFuture<HttpResponse<String>> response = HttpClient.newBuilder().build().sendAsync(request, HttpResponse.BodyHandler.asString());
		response.thenAccept(t -> System.out.println(t.body())).get();		
	}
	
	@Test
	public void setExecutorForSendAsync() throws Exception {
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		HttpRequest request = HttpRequest.newBuilder(new URI("http://www.google.com")).GET().build();
		CompletableFuture<HttpResponse<String>> response = HttpClient.newBuilder()
				.executor(executorService).build()
				.sendAsync(request, HttpResponse.BodyHandler.asString());
		
		//create a new request, if not nullpointerexception is thrown
		request = HttpRequest.newBuilder(new URI("http://www.google.com")).GET().build();
		CompletableFuture<HttpResponse<String>> response2 = HttpClient.newBuilder()
				.executor(executorService).build()
				.sendAsync(request, HttpResponse.BodyHandler.asString());
		
		response.thenAccept(t -> System.out.println(t.body())).get();
		response2.thenAccept(t -> System.out.println(t.body())).get();
	}
	
	@Test
	public void setClientNotAllowingCookies() {
		HttpClient.newBuilder().cookieManager(new CookieManager(null, CookiePolicy.ACCEPT_NONE)).build();
	}
	
	@Test
	public void cookies() throws Exception {
		
		HttpRequest request = HttpRequest.newBuilder(new URI("http://www.google.com")).GET().build();
		HttpClient client = HttpClient.newBuilder().build();
		client.cookieManager().ifPresent(t -> t.getCookieStore().getCookies().forEach(c -> System.out.println(c.getName())));
	}
}

