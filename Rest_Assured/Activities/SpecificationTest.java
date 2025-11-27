package examples;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecificationTest {
	
	// Declare Request and response specification objects
	
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	int petId;
	
	@BeforeClass //Setup function 
	public void setUp() {
		// create the request specification
		reqSpec = new RequestSpecBuilder().
			setBaseUri("https://petstore.swagger.io/v2").
			addHeader("Content-Type", "application/json").
			build();
		
		// create the response specification
		resSpec = new ResponseSpecBuilder().
			expectStatusCode(200).
			expectBody("status", Matchers.equalTo("alive")).
			expectResponseTime(Matchers.lessThanOrEqualTo(5000L)).
			build();
	}
	
	// POST https://petstore.swagger.io/v2/pet
	@Test(priority=1)
	public void postRequestTest() {
		// File inputfile = new File("src/test/resources/pet.json");
		// creating map
		Map<String, Object> reqBody = new HashMap<>();
		reqBody.put("id", 37801);
		reqBody.put("name", "jim");
		reqBody.put("status", "alive");
		// send request and save the response
		Response response = 
			given().
			spec(reqSpec).body(reqBody).
			log().all().
		when().
		    post("/pet");
		// Extract the pet id from response
		this.petId = response.then().extract().path("id");
		// Assertions
		response.then().spec(resSpec).log().all();
	}
	
	// GET https://petstore.swagger.io/v2/pet/{petId}
	@Test(priority=2)
	public void getRequestTest() {
		// send request, receive response and assert 
		given().spec(reqSpec).pathParam("petId",this.petId).
		when().get("/pet/{petId}").
		then().spec(resSpec);
	}
	
	// DELETE https://petstore.swagger.io/v2/pet/{petId}
	@Test(priority=3)
	public void deleteRequestTest() {
		// send request, receive response and assert 
		given().spec(reqSpec).pathParam("petId",this.petId).
		when().delete("/pet/{petId}").
		then().statusCode(200).body("message", Matchers.equalTo(""+this.petId));	
	}
	
}
