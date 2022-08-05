package mainTest;


import static org.testng.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

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
		
		assertEquals(response.jsonPath().getString("features[1].properties.title"), "M 4.3 - 7 km ENE of Sh?h?b?d, India");
//		System.out.println(response.jsonPath().getList("features"));
//		String json = response.jsonPath().getString("features..properties.title");
		String obbj = response.jsonPath().getString("features[1]").toString();
		System.out.println(obbj);
		String jsonObj = response.asPrettyString();
		System.out.println(jsonObj);
//		String[] jArray = response.jsonPath().getJsonObject("features..properties.title");
//		System.out.println(jArray.length);
//		
	}
	
}
