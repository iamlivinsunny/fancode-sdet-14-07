package org.assignment.response;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

import java.util.List;
import java.util.Map;

public class RestAPIResponse implements Response {
    ValidatableResponse validatableResponse;
    JsonPath path;

    public RestAPIResponse(ValidatableResponse validatableResponse) {
        this.validatableResponse = validatableResponse;
        this.path = new JsonPath(validatableResponse.extract().asString());
    }

    @Override
    public ValidatableResponse getResponseObject() {
        return this.validatableResponse;
    }

    @Override
    public List extractDataAsList(String jsonPathExtractString) {
        return this.path.getList(jsonPathExtractString);
    }

    public Map extractDataAsMap(String jsonPathExtractString) {
        return this.path.getMap(jsonPathExtractString);
    }

    public String extractDataAsString(String jsonPathExtractString) {
        return this.path.getString(jsonPathExtractString);
    }
}
