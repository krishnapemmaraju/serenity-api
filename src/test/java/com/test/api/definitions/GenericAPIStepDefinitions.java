package com.test.api.definitions;

import java.util.Map;

import org.junit.Assert;

import com.test.api.implementation.GenericAPIStepImp;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.thucydides.core.annotations.Steps;

public class GenericAPIStepDefinitions {

	@Steps
	GenericAPIStepImp apiStepImpl;

	private Response response;
	
	@Given("client uses the following url parameters:")
	public void the_client_uses_the_following_url_parameters(Map<String, String> params) {
		  apiStepImpl.setEndPointURL(params);
	}

	@When("the client invokes {string} with resource as {string}")
	public void the_client_invokes_endpoint_with_resource_as(String endpoint, String resource) {
		this.response = apiStepImpl.callHttpEndPoint(endpoint, resource);
	}

	@Then("the client should get {int} response code")
	public void the_client_should_get_response_code(int httpCode) {
		Assert.assertEquals(httpCode, response.getStatusCode());
		Serenity.recordReportData().withTitle("Response Code : ").andContents("Response Code " + Integer.toString(response.getStatusCode()) + " Generated Successfully");
	}

	@Then("the client should see forecast feeds for {string} location in response for {string}")
	public void the_client_should_see_forecast_feeds_for_location_in_response(String locationName, String jsonPath) {
		Ensure.that(apiStepImpl.validateResponseBody(this.response, jsonPath)).isEqualTo(locationName);
		Serenity.recordReportData().withTitle("Forecast data feed fetched from the Location is : ").andContents(apiStepImpl.validateResponseBody(this.response, jsonPath));
	}

}
