package com.test.api.implementation;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.test.api.RestAPIEndPoints;
import com.test.utils.Utils;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class GenericAPIStepImp {
	
	public String endPointURL ="";	
	/* sample Strigng return URL */
	
	@Step("Frame the Request URL")
	public String frameTheURL(String endpoint,String resource) {
		return frameURLWithAPIKey( endpoint,resource,"");
	}
	
	
	@Step("Call the API Endpoint")
	public Response callHttpEndPoint(String endpoint,String resource) {
		return callHttpEndPointWithURLAPIKey( endpoint,resource,"");
	}
	
	
	public String endPoint(Map<String,String> params) {
		return params.keySet().stream()
			   .map(key -> key + "=" + params.get(key))
			   .collect(Collectors.joining("&","?",""));
	}
	
	//Method that gets a Map of (key,value) pair for query parameters and calls a private method to generate the query string. Then sets it as a instance variable
    public void setEndPointURL(Map<String,String> parameters) {
        this.endPointURL = endPoint(parameters);
    }
    
    public Response callHttpEndPointWithURL(String webServiceEndpoint,String resource,String body) {
    	String restEndPoint = RestAPIEndPoints.URL+RestAPIEndPoints.resource+resource+this.endPointURL;
        String Httpmethod = RestAPIEndPoints.method;
        return callEndpointHttpMethod(restEndPoint, Httpmethod,"");
    }
    
    public Response callHttpEndPointWithURLAPIKey(String webServiceEndpoint,String resource,String body) {
    	String restEndPoint = RestAPIEndPoints.URL + RestAPIEndPoints.resource+resource+this.endPointURL+"&key="+Utils.getApiKey("apikey");
        String Httpmethod = RestAPIEndPoints.method;
        System.out.println(restEndPoint);
        return callEndpointHttpMethod(restEndPoint, Httpmethod,"");
    }
    
    // return the STring after appending the query parameters
    public String frameURLWithAPIKey(String webServiceEndpoint,String resource,String body) {
    	String restEndPoint = RestAPIEndPoints.URL + RestAPIEndPoints.resource+resource+this.endPointURL+"&key="+Utils.getApiKey("apikey");
        String Httpmethod = RestAPIEndPoints.method;
        System.out.println(restEndPoint);
        String finalEndPont = restEndPoint;
        return restEndPoint;
    }
    
    private Response callEndpointHttpMethod(String url, String method, String body) {
        switch (method) {
            case "GET":
                return callHttpEndPointWithMethodGet(url);
            case "POST":
                return callHttpEndPointWithPost(url, body);
            default:
                throw new Error("HTTP Method" + method + "is not supported");
        }
    }
    
    private Response callHttpEndPointWithMethodGet(String url) {
        Response response = SerenityRest.given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .get(url);
        return response;

    }

    private Response callHttpEndPointWithPost(String url, String body) {
        Response response = SerenityRest.given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(url);
        return response;
    }
    
    

}
