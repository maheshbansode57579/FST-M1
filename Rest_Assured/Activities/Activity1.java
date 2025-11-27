package activities;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Activity1 {
	
	// https://petstore.swagger.io/v2/pet
	
	@Test(priority=1)
	public void addNewPet() {
		Map <String, Object> reqBody = new HashMap<>();
		reqBody.put("id", 77232);
		reqBody.put("name", "Riley");
		reqBody.put("status", "alive");
		
		Response response = given().
				baseUri("https://petstore.swagger.io/v2/pet"). // Set base URI
				header("Content-Type", "application/json"). // Set headers
				body(reqBody). // Add request body
				when().post(); // Send POST request
		
		// Assertion
		response.then().body("id", Matchers.equalTo(77232));
		response.then().body("name", Matchers.equalTo("Riley"));
		response.then().body("status", Matchers.equalTo("alive"));
	}
	
	// https://petstore.swagger.io/v2/pet/{petId}
	
	@Test(priority=2)
	public void getPetInfo() {
		Response response = given().
				baseUri("https://petstore.swagger.io/v2/pet"). // Set base URI
				header("Content-Type", "application/json"). // Set headers
				when().pathParam("petId", 77232). // Set path parameter
				get("/{petId}"); // Send GET request
		
		// Assertion
		response.then().body("id", Matchers.equalTo(77232));
		response.then().body("name", Matchers.equalTo("Riley"));
		response.then().body("status", Matchers.equalTo("alive"));
	}
	
	// https://petstore.swagger.io/v2/pet/{petId}
	
	@Test(priority = 3)
	public void deletePet() {
		Response response = given()
			.baseUri("https://petstore.swagger.io/v2/pet") // Set base URI
			.header("Content-Type", "application/json") // Set headers
			.when().pathParam("petId", 77232) // Set path parameter
			.delete("/{petId}"); // Send DELETE request
 
		// Assertion
		response.then().body("code", Matchers.equalTo(200));
		response.then().body("message", Matchers.equalTo("77232"));
	}
}
