package com.test.buildapi;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.test.utils.Utils;

import io.restassured.response.Response;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.util.EnvironmentVariables;

public class BuildAPIUtilities {
	
	public String endPointURL;
	private EnvironmentVariables env;
	public Map<String,String> queryParam = new HashMap<String,String>();
	
	public String baseURL() {
		return EnvironmentSpecificConfiguration.from(env).getProperty("restapi.baserul");
	}

	public static String endPoint(Map<String, String> params) {
		return params.keySet().stream().map(key -> key + "=" + params.get(key))
				.collect(Collectors.joining("&", "?", ""));
	}

	// Method that gets a Map of (key,value) pair for query parameters and calls a
	// private method to generate the query string. Then sets it as a instance
	// variable
	public void setEndPointURL(Map<String, String> parameters) {

		/*
		 * the below code will actually mitigate the calling environment parameters from
		 * environment properties file
		 * 
		 * Map<String,String> params = new HashMap<String,String>();
		 * params.put("res",Serenity.getCurrentSession().get("res").toString());
		 * params.put("key", Utils.getApiKey("apikey"));
		 */
		this.endPointURL = endPoint(parameters);
	}

	public  Response callHttpGetEndPointWithURL(String webServiceEndpoint, String resource, String body) {
		String restEndPoint = baseURL() + resource + this.endPointURL;
		return callEndpointHttpMethod(restEndPoint,  APIHttpMethods.HttpGetMethod.trim(), "");
	}
	
	public  Response callHttpPostEndPointWithURL(String webServiceEndpoint, String resource, String body) {
		String restEndPoint = baseURL() + resource + this.endPointURL;
		return callEndpointHttpMethod(restEndPoint, APIHttpMethods.HttpPostMethod.trim(),body);
	}
	
	public  Response callHttpPutEndPointWithURL(String webServiceEndpoint, String resource, String body) {
		String restEndPoint = baseURL() + resource + this.endPointURL;
		return callEndpointHttpMethod(restEndPoint,APIHttpMethods.HttpPutMethod.trim(),body);
	}
	
	public  Response callHttpDeleteEndPointWithURL(String webServiceEndpoint, String resource, String body) {
		String restEndPoint = baseURL() + resource + this.endPointURL;
		return callEndpointHttpMethod(restEndPoint, APIHttpMethods.HttpDeleteMethod.trim(), "");
	}

	public  Response callHttpGetEndPointWithURLAPIKey(String webServiceEndpoint, String resource, String body) {
		String restEndPoint = baseURL() + resource.trim() + this.endPointURL + "&key="
				+ Utils.getApiKey("apikey").trim();
		return callEndpointHttpMethod(restEndPoint, APIHttpMethods.HttpGetMethod.trim(), "");
	}
	
	public  Response callHttpPostEndPointWithURLAPIKey(String webServiceEndpoint, String resource, String body) {
		String restEndPoint = baseURL() + resource.trim() + this.endPointURL + "&key="
				+ Utils.getApiKey("apikey").trim();
		System.out.println(restEndPoint);
		return callEndpointHttpMethod(restEndPoint, APIHttpMethods.HttpPostMethod.trim(),body);
	}
	
	public  Response callHttpPutEndPointWithURLAPIKey(String webServiceEndpoint, String resource, String body) {
		String restEndPoint = baseURL() + resource.trim() + this.endPointURL + "&key="
				+ Utils.getApiKey("apikey").trim();
		System.out.println(restEndPoint);
		return callEndpointHttpMethod(restEndPoint, APIHttpMethods.HttpPutMethod.trim(),body);
	}
	
	public  Response callHttpDeleteEndPointWithURLAPIKey(String webServiceEndpoint, String resource, String body) {
		String restEndPoint = baseURL() + resource.trim() + this.endPointURL + "&key="
				+ Utils.getApiKey("apikey").trim();
		System.out.println(restEndPoint);
		return callEndpointHttpMethod(restEndPoint, APIHttpMethods.HttpDeleteMethod.trim(),"");
	}

	// return the  full Endpoint after appending the query parameters
	public  String frameURLWithAPIKey(String webServiceEndpoint, String resource, String body) {
		String restEndPoint = baseURL() + resource.trim() + this.endPointURL + "&key="
				+ Utils.getApiKey("apikey").trim();
		return restEndPoint;
	}

	private static Response callEndpointHttpMethod(String url, String method, String body) {
		switch (method) {
		case "GET":
			return callHttpEndPointWithMethodNoBody(url);
		case "POST":
			return callHttpEndPointPostWithBody(url, body);
		case "PUT":
			return callHttpEndPointPostWithBody(url, body);
		case "DELETE":
			return callHttpEndPointWithMethodNoBody(url);
		default:
			throw new Error("HTTP Method" + method + "is not supported");
		}
	}

	private static Response callHttpEndPointWithMethodNoBody(String url) {
		Response response = SerenityRest.given().contentType("application/json")
				.header("Content-Type", "application/json").get(url);
		return response;

	}

	private static Response callHttpEndPointPostWithBody(String url, String body) {
		Response response = SerenityRest.given().contentType("application/json")
				.header("Content-Type", "application/json").body(body).when().post(url);
		return response;
	}
	

	public String validateResponseBody(Response response, String jsonPath) {
		return response.getBody().jsonPath().get(jsonPath).toString().trim();
	}

}
