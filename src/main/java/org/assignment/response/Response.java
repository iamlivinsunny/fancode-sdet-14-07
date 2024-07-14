package org.assignment.response;

import io.restassured.response.ValidatableResponse;

import java.util.List;
import java.util.Map;

public interface Response {
    ValidatableResponse getResponseObject();

    List extractDataAsList(String jsonPath);

    Map extractDataAsMap(String jsonPath);

    String extractDataAsString(String jsonPath);

    void writeRequestDetailsToFile();
}
