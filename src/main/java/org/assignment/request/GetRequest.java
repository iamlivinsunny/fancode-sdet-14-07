package org.assignment.request;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.assignment.response.RestAPIResponse;
import org.assignment.utlities.FileUtilities;

import static io.restassured.RestAssured.given;

public class GetRequest implements Request {
    String environment;
    String endpoint;

    /**
     * Stores the environment and the endpoint as instance variables
     *
     * @param environment String environment for the request execution
     * @param endpoint  String endpoint of the request
     */
    public GetRequest(String environment, String endpoint) {
        this.environment = environment;
        this.endpoint = endpoint;
    }

    /**
     * This method executes the request and return the response object
     *
     * @return  RestAPIResponse returns an instance of rest api response which can be used for further processing
     */
    @Override
    public RestAPIResponse getResponse() {
        return new RestAPIResponse(given().baseUri(environment).contentType(ContentType.JSON).when().get(endpoint).then());
    }

    /**
     * This method executes the request, store the api response in the provided file path and return the response object
     * @param filePathToWriteResponse   String path for storing the response of execution
     * @return  RestAPIResponse returns an instance of rest api response which can be used for further processing
     */
    @Override
    public RestAPIResponse getResponse(String filePathToWriteResponse) {
        ValidatableResponse validatableResponse = given().baseUri(environment).contentType(ContentType.JSON).when().get(endpoint).then();
        FileUtilities.writeResponseToFile(filePathToWriteResponse, validatableResponse.extract().response().asString());
        return new RestAPIResponse(validatableResponse);
    }
}
