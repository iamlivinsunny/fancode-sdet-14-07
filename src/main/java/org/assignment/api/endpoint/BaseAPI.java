package org.assignment.api.endpoint;

import java.io.File;

public abstract class BaseAPI {
    public final static String environment = "https://jsonplaceholder.typicode.com/";
    public static String responsePath;

    {
        responsePath = System.getProperty("user.dir") + "/src/test/resources/api-response/".replace("/", File.separator);
    }
}
