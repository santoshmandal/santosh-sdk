package com.movietest.api.movies.client.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class MovieRestClientErrorHandler implements ResponseErrorHandler {

    Logger logger = LoggerFactory.getLogger(MovieRestClientErrorHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {
        logger.info("4XX: " + httpResponse.getStatusCode().is4xxClientError() + "   5XX: " + httpResponse.getStatusCode().is5xxServerError());
        return (httpResponse.getStatusCode().is4xxClientError()
                || httpResponse.getStatusCode().is5xxServerError());
    }

    @Override
    public void handleError(ClientHttpResponse response)
            throws IOException {

        if (response.getStatusCode().is4xxClientError()
                || response.getStatusCode().is5xxServerError()) {
            throw new MovieRestClientException("", response.getStatusCode().value(), response.getStatusCode().toString());
        }
    }
}
