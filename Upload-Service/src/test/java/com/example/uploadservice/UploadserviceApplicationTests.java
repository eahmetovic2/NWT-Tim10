package com.example.uploadservice;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.example.uploadservice.*;
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
public class UploadserviceApplicationTests {

/*
	@Test
	public void testPredmeti() throws URISyntaxException
	{

		RestTemplate restTemplate = new RestTemplate();
		
		final String url = "http://localhost:8000/predmeti";
		URI uri = new URI(url);

		restTemplate.getForEntity(uri, String.class);

		final String url2 = "http://localhost:8000/predmeti";
		uri = new URI(url2);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		Assert.assertTrue(result.getBody().contains("\"naziv\":\"IM1\""));
	}

	@Test
	public void testAddPredmet() throws URISyntaxException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8000/predmet");
	
		String json = "{\"nastavnikId\":1,\"naziv\":\"NWT\"}";
		StringEntity entity = new StringEntity(json);
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
	
		CloseableHttpResponse response = client.execute(httpPost);
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		client.close();
	} 

	@Test
	public void testGetPredmetById() throws URISyntaxException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		final String url2 = "http://localhost:8000/predmet/2";
		URI uri = new URI(url2);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		Assert.assertTrue(result.getBody().contains("\"naziv\":\"IM1\""));
	}*/
}