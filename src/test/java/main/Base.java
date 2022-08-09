package main;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ConfigRead;

public class Base {
	String configFilename;
	Properties configProperties;
	String currentWorkingDirectory;
	String uri;
	Response response;

	public void preSetup() throws IOException {
		currentWorkingDirectory = System.getProperty("user.dir");
		configFilename = currentWorkingDirectory + "/src/test/resources/config/config.properties";
		configProperties = ConfigRead.readConfigProperties(configFilename);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime systemTime = LocalDateTime.now();
		String startTime = dtf.format(systemTime);
		
		//Initializing RestAssured api for getting the earthquake data
		RestAssured.baseURI = configProperties.getProperty("baseUrl");
		uri = "/fdsnws/event/1/query&format=geojson" 
		        + "&starttime="+ startTime 
		        + "&minlatitude=" + configProperties.getProperty("minlatitude") 
		        + "&maxlatitude=" + configProperties.getProperty("maxlatitude") 
		        + "&minlongitude=" + configProperties.getProperty("minlongitude")
		        + "&maxlongitude=" + configProperties.getProperty("maxlongitude");
		
		response = RestAssured.given().contentType(ContentType.JSON).when().get(uri).then().extract()
				.response(); 
	}

}
