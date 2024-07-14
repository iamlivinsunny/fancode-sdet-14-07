package org.assignment.response;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

import java.util.List;
import java.util.Map;

public class RestAPIResponse implements Response {
    ValidatableResponse validatableResponse;
    JsonPath path;

    /**
     * Accepts ValidatableResponse as an argument and store as instance variable for further processing
     *
     * @param validatableResponse io.restassured.response.ValidatableResponse
     */
    public RestAPIResponse(ValidatableResponse validatableResponse) {
        this.validatableResponse = validatableResponse;
        this.path = new JsonPath(validatableResponse.extract().asString());
    }

    /**
     * Return the constructor argument for custom processing
     *
     * @return io.restassured.response.ValidatableResponse
     */
    @Override
    public ValidatableResponse getResponseObject() {
        return this.validatableResponse;
    }

    /**
     * This method is used to extract data from the api response
     *
     * @param jsonPathExtractString jsonPath to extract data from the response
     * @return  List<Object> list of results identified
     */
    @Override
    public List extractDataAsList(String jsonPathExtractString) {
        return this.path.getList(jsonPathExtractString);
    }

    /**
     * This method is used to extract data from the api response
     *
     * @param jsonPathExtractString jsonPath to extract data from the response
     * @return  Map<Object, Object> Map of results identified
     */
    @Override
    public Map extractDataAsMap(String jsonPathExtractString) {
        return this.path.getMap(jsonPathExtractString);
    }

    /**
     * This method is used to extract data from the api response
     *
     * @param jsonPathExtractString jsonPath to extract data from the response
     * @return  String of results identified
     */
    @Override
    public String extractDataAsString(String jsonPathExtractString) {
        return this.path.getString(jsonPathExtractString);
    }

    /**
     * Used to write response to a file
     */
    @Override
    public void writeRequestDetailsToFile(){
        this.validatableResponse.extract().response().asString();
    }
}
