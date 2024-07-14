package org.assignment.request;

import org.assignment.response.Response;

public interface Request {
    Response getResponse();
    Response getResponse(String filePathToWriteResponse);

}
