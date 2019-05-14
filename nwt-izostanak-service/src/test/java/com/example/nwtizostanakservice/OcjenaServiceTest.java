package com.example.nwtizostanakservice;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.example.nwtizostanakservice.*;
import com.netflix.client.http.HttpResponse;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import io.reactivex.netty.protocol.http.client.HttpClientBuilder;
import junit.framework.Assert;

@SpringBootTest
public class OcjenaServiceTest {
	/*


	@Test
	public void testUcenici() throws URISyntaxException
	{
		RestTemplate restTemplate = new RestTemplate();
		
		final String url = "http://localhost:8001/start";
		URI uri = new URI(url);

		restTemplate.getForEntity(uri, String.class);

		final String url2 = "http://localhost:8001/ucenik/sve";
		uri = new URI(url2);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		Assert.assertTrue(result.getBody().contains("\"ime\":\"Hamza\",\"prezime\":\"Iseric\""));
	}

	@Test
	public void testAddNastavnik() throws URISyntaxException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8001/nastavnik/create");
	
		String json = "{\"id\":0,\"ime\":\"Ehvan\",\"prezime\":\"Građanin2\"}";
		StringEntity entity = new StringEntity(json);
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
	
		CloseableHttpResponse response = client.execute(httpPost);
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		client.close();
	} 

	@Test
	public void testAddUcenik() throws URISyntaxException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8001/ucenik/create");
	
		String json = "{\"id\":10,\"ime\":\"Ehvan\",\"prezime\":\"Građanin2\"}";
		StringEntity entity = new StringEntity(json);
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
	
		CloseableHttpResponse response = client.execute(httpPost);
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		client.close();
	} 
	@Test
	public void testNastavnici() throws URISyntaxException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		
		final String url = "http://localhost:8001/start";
		URI uri = new URI(url);

		restTemplate.getForEntity(uri, String.class);

		final String url2 = "http://localhost:8001/nastavnik/sve";
		uri = new URI(url2);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		Assert.assertTrue(result.getBody().contains("\"ime\":\"Ehvan\",\"prezime\":\"Građanin\""));
	} 
	*/
}