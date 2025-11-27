package examples;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FirstTest {
	
	// GET https://petstore.swagger.io/v2/pet/findByStatus?status=sold
	
	@Test
	public void getRequestWithQueryParam() {
		// Send Request and save the response
		Response response=RestAssured.given().
		// Request spec
		baseUri("https://petstore.swagger.io/v2").
		header("Content-Type","application/json").
		queryParam("status","sold").
		// send request and receive response
		when().get("/pet/findByStatus");
		
//		// get response status code
//		System.out.println("Status code is: "+response.getStatusCode());
//		// get response header
//		System.out.println("Headers are: \n"+response.getHeaders());
//		// get response body
//		System.out.println("Body: \n"+response.getBody().asString());
//		System.out.println("Body: \n"+response.getBody().asPrettyString());
		
		// Extract Json properties using Json path
		String petStatus = response.then().extract().path("[0].status");
		
		//Assertions
		Assert.assertEquals(petStatus, "sold");
		
		response.then().
		// response spec
		statusCode(0).body("[0].status", Matchers.equalTo("sold"));
		
	}
	
	// GET https://petstore.swagger.io/v2/pet/{petId}

	@Test
	public void getRequestWithPathParam() {
		// Send Request and save the response
		RestAssured.given().
		// Request spec
				baseUri("https://petstore.swagger.io/v2").
				header("Content-Type", "application/json")
				.pathParam("petId", 12).
				// send request and receive response
				when().get("/pet/{petId}").
				then().
				statusCode(200).
				body("id", Matchers.equalTo(12));
	}

}
