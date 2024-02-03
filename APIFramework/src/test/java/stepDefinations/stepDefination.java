package stepDefinations;

import java.io.IOException;

import java.util.ArrayList;

import java.util.List;

import io.restassured.response.Response;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import resources.APIResources;
//import pojo.AddPlace;
//import pojo.Location;
import resources.TestDataBuild;
import resources.Utils;


public class stepDefination extends Utils{
RequestSpecification res;
ResponseSpecification resspec;
Response response;
TestDataBuild data =new TestDataBuild();
JsonPath js;
static String place_id;
String resource;
@Given("Add Place Payload with {string} {string} {string}")
public void add_place_payload_with(String name, String language, String address) throws IOException {
    // Write code here that turns the phrase above into concrete actions

res=given().spec(requestspecification())
.body(data.addPlacePayload(name, language, address));
}
@When("User calls {string} with {string} http request")
public void user_calls_with_http_request(String resource, String method) {
   
    //System.out.println(string);
	APIResources resourceAPI=APIResources.valueOf(resource);
	System.out.println(resourceAPI.getresource());
	resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
   if(method.equalsIgnoreCase("POST"))
	response =res.when().post(resourceAPI.getresource());
   else if(method.equalsIgnoreCase("GET"))
		response =res.when().get(resourceAPI.getresource());

    		String responseString=response.asString();
    		System.out.println(responseString);
}
@Then("the API call is success with status code {int}")
public void the_api_call_is_success_with_status_code(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
   // System.out.println(int1);
    assertEquals(response.statusCode(),200);
}
@Then("{string} in response body is {string}")
public void in_response_body_is(String keyValue, String ExpectedValue) {
    // Write code here that turns the phrase above into concrete actions
    //System.out.println(string);
    //System.out.println(string2);
    assertEquals(getJsonPath(response,keyValue),ExpectedValue);
    
}
@Then("verify placeid created maps to {string} using {string}")
public void verify_placeid_created_maps_to_using(String ExpectedName, String resource) throws IOException {
    // Write code here that turns the phrase above into concrete actions
	place_id=getJsonPath(response,"place_id");
    res=given().spec(requestspecification()).queryParam("place_id",place_id);
	user_calls_with_http_request(resource,"GET");
	String Actualname=getJsonPath(response,"name");
	assertEquals(Actualname,ExpectedName);
		
		
}
@Given("DeletePlace Payload")
public void delete_place_payload() throws IOException {
    // Write code here that turns the phrase above into concrete actions
	res=given().spec(requestspecification()).body(data.DeletePlacePayload(place_id));
}

}
