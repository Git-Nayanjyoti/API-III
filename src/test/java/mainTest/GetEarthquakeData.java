package mainTest;


import java.util.List;

import org.testng.annotations.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jsonObjects.MainObject;
import jsonObjects.features; 

public class GetEarthquakeData{
	

	@Test
	public void getEqdata() {
		
		RestAssured.baseURI = "https://earthquake.usgs.gov";
		String uri = "/fdsnws/event/1/query&format=geojson"
				+ "&starttime=2018-07-31"
				+ "&endtime=2022-08-05"
				+ "&minlatitude=15"
				+ "&maxlatitude=20"
				+ "&minlongitude=70"
				+ "&maxlongitude=80";

		Response response = RestAssured.given().contentType(ContentType.JSON)
		.when()
		.get(uri)
		.then()
		.extract()
		.response();
		
		String jsonObj = response.asPrettyString();
		
		MainObject mainObject = new Gson().fromJson(jsonObj, MainObject.class);
		List<features> feature = mainObject.features;
		for(features property: feature) {
			System.out.println(property.properties.title);
		}

	}
	
}
