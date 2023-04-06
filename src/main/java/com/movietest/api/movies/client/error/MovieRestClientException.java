package com.movietest.api.movies.client.error;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class MovieRestClientException extends RuntimeException {
    private String serviceName;
    private int statusCode;
    private String error;

    public MovieRestClientException(String serviceName, int statusCode, String error) {
        super();
        this.serviceName = serviceName;
        this.statusCode = statusCode;
        this.error = error;
    }
    public String getServiceName() {
        return serviceName;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public String getError() {
        return error;
    }
}

