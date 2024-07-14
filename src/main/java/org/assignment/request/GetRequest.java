package org.assignment.request;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.assignment.response.RestAPIResponse;
import org.assignment.utlities.FileUtilities;

import static io.restassured.RestAssured.given;

public class GetRequest implements Request {
    String environment;
    String endpoint;

    public GetRequest(String environment, String endpoint) {
        this.environment = environment;
        this.endpoint = endpoint;
    }

    @Override
    public RestAPIResponse getResponse() {
        return new RestAPIResponse(given().baseUri(environment).contentType(ContentType.JSON).when().get(endpoint).then());
    }

    @Override
    public RestAPIResponse getResponse(String filePathToWriteResponse) {
        ValidatableResponse validatableResponse = given().baseUri(environment).contentType(ContentType.JSON).when().get(endpoint).then();
        FileUtilities.writeResponseToFile(filePathToWriteResponse, validatableResponse.extract().response().asString());
        return new RestAPIResponse(validatableResponse);
    }
}
