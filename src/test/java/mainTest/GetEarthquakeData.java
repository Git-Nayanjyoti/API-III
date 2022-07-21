package mainTest;


import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GetEarthquakeData{
	

	@Test
	public void getEqdata() {
		RestAssured.baseURI = "https://riseq.seismo.gov.in/riseq/Earthquake/recent_earthquake";
		String payLoad = "?region:I"+
				"&days:C" +
				"&start_time:19-07-2022" +
				"&end_time:21-07-2022" +
				"&magnitude-min:0" +
				"&magnitude-max:10" +
				"&depth-min:0" +
				"&depth-max:1000"+
				"&timezone:2"+
				"&submit:Apply";
//		String uri = "https://riseq.seismo.gov.in/riseq/Earthquake/recent_earthquake?region=I&region_lat_2=&region_long_1=&region_long_2=&region_lat_1=&point_lat=&point_long=&point_distance=&days=C&start_time=10-07-2021&end_time=21-07-2022&magnitude-min=2.3&magnitude-max=10&depth-min=58&depth-max=1000&timezone=2&submit=Apply";
//		String uri = "riseq/Earthquake/recent_earthquake";
		RestAssured.given().contentType(ContentType.JSON)
		.when()
//		.queryParam("format", "geojson")
//		.queryParam("starttime", "2022-07-20")
//		.queryParam("endtime", "2022-07-21")
//		.get(uri)
		.post(payLoad)
		.then()
		.log()
		.all()
		.extract().response();
	}
	
}
