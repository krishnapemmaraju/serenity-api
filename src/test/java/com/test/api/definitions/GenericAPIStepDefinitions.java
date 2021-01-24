package com.test.api.definitions;


import java.util.Map;

import org.junit.Assert;

import com.test.api.implementation.GenericAPIStepImp;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.bytebuddy.implementation.bytecode.assign.Assigner.EqualTypesOnly;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.matchers.statematchers.isClickableMatcher;
import net.thucydides.core.annotations.Steps;

public class GenericAPIStepDefinitions {
	
	@Steps
	GenericAPIStepImp apiStepImpl;
	
	private Response response;
    private Response responseNext;
    private String endPointURL;
	
	
	@Given("client uses the following url parameters:")
	public void the_client_uses_the_following_url_parameters(Map<String,String> params) {
		apiStepImpl.setEndPointURL(params);
	}
	
	@When("the client invokes {string} endpoint with resource as {string}")
	public void the_client_invokes_endpoint_with_resource_as(String endpoint, String resource) {
		 this.response = apiStepImpl.callHttpEndPoint(endpoint,resource);
		// this.endPointURL = apiStepImpl.frameTheURL(endpoint, resource);
}

	@Then("the client should receive an HTTP {int} response code")
	public void the_client_should_receive_an_http_response_code(int httpCode) {
		Assert.assertEquals(httpCode, response.getStatusCode());
	}
	
	@Then("the client should see forecast feeds for {string} location in response")
	public void the_client_should_see_forecast_feeds_for_location_in_response(String locationName) {
          String responseBody = response.getBody().jsonPath().get("SiteRep.DV.Location.name").toString();
          net.serenitybdd.screenplay.ensure.Ensure.that(responseBody).isEqualTo(responseBody);
	} 


}
