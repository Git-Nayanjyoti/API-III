package mainTest;


import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetEarthquakeData{
	

	@Test
	public void getEqdata() {
		
		//https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2018-07-31&endtime=2022-08-05&minlatitude=15&maxlatitude=20&minlongitude=70&maxlongitude=80
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
		.log()
		.all()
		.extract().response();
		
		response.jsonPath().getString("$.features..properties.title");
		
	}
	
}
