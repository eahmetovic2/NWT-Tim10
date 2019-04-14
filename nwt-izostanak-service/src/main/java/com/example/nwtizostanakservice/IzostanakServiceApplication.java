package com.example.nwtizostanakservice;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients 
@RibbonClient(name="izostanak-service", configuration = RibbonConfiguration.class)
public class IzostanakServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IzostanakServiceApplication.class, args);
		
	}

	/* @EventListener({ApplicationReadyEvent.class})
	void applicationReadyEvent() {
		System.out.println("Application started ... launching browser now");
		Browse("http://localhost:8080/pocetna");
	} */

	public static void Browse(String url) {
		String os = System.getProperty("os.name").toLowerCase();
			Runtime rt = Runtime.getRuntime();
		
		try{
	
			if (os.indexOf( "win" ) >= 0) {
	
				// this doesn't support showing urls in the form of "page.html#nameLink" 
				rt.exec( "rundll32 url.dll,FileProtocolHandler " + url);
	
			} else if (os.indexOf( "mac" ) >= 0) {
	
				rt.exec( "open " + url);
	
				} else if (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0) {
	
				// Do a best guess on unix until we get a platform independent way
				// Build a list of browsers to try, in this order.
				String[] browsers = {"firefox", "mozilla", "konqueror",
										"netscape","opera","links","lynx"};
					
				// Build a command string which looks like "browser1 "url" || browser2 "url" ||..."
				StringBuffer cmd = new StringBuffer();
				for (int i=0; i<browsers.length; i++)
					cmd.append( (i==0  ? "" : " || " ) + browsers[i] +" \"" + url + "\" ");
					
				rt.exec(new String[] { "sh", "-c", cmd.toString() });
	
			   } else {
					return;
			   }
		   }catch (Exception e){
			return;
		   }
		  return;	

		/*
		if(Desktop.isDesktopSupported()){
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.browse(new URI(url));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		}else{
			Runtime runtime = Runtime.getRuntime();
			try {
				runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		*/
	}
	

}
