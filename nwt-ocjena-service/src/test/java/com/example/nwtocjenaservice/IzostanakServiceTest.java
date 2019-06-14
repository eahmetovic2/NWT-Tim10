package com.example.nwtocjenaservice;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.example.nwtocjenaservice.model.Predmet;
import com.example.nwtocjenaservice.proxy.OcjenaIzostanakServiceProxy;
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
public class IzostanakServiceTest {
	/*


	@Test
	public void testIzostanci() throws URISyntaxException
	{
		RestTemplate restTemplate = new RestTemplate();
		
		final String url = "http://localhost:8002/pocetna";
		URI uri = new URI(url);

		restTemplate.getForEntity(uri, String.class);

		final String url2 = "http://localhost:8002/izostanak/sve";
		uri = new URI(url2);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		Assert.assertTrue(result.getBody().contains("\"ime\":\"John\",\"prezime\":\"Doe\""));
	}

	@Test
	public void testAddPredmet() throws URISyntaxException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8002/predmet/create");
	
		String json = "{\"id\":5,\"naziv\":\"NWT\"}";
		StringEntity entity = new StringEntity(json);
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
	
		CloseableHttpResponse response = client.execute(httpPost);
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		client.close();
	} 
	*/
}