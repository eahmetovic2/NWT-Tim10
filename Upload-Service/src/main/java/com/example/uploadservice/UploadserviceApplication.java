package com.example.uploadservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients 
@RibbonClient(name="upload-service", configuration = RibbonConfiguration.class)
public class UploadserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadserviceApplication.class, args);
	}

}
