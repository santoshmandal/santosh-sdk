package com.movietest.api.movies.client;

import com.movietest.api.movies.client.error.MovieRestClientErrorHandler;
import com.movietest.api.movies.client.error.MovieRestClientException;
import com.movietest.api.movies.entities.Movie;
import com.movietest.api.movies.entities.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class MovieRestClient {

    Logger logger = LoggerFactory.getLogger(MovieRestClient.class);

    @Value("${movies.client.connect.timeout}")
    int connectTimeout;

    @Value("${movies.client.connect.read.timeout}")
    int readTimeout;

    @Value("${movies.api.end.point}")
    String apiEndpoint;

    @Value("${movies.api.get.movies.uri}")
    String allMoviesURI;

    @Value("${movies.api.get.movie.uri}")
    String movieByIdURI;

    @Value("${movies.api.get.quote.uri}")
    String quoteByIdURI;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        restTemplate.setErrorHandler(new MovieRestClientErrorHandler());
        return restTemplate;
    }

    //Override timeouts in request factory
    private SimpleClientHttpRequestFactory getClientHttpRequestFactory() {

        SimpleClientHttpRequestFactory clientHttpRequestFactory  = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(connectTimeout);
        clientHttpRequestFactory.setReadTimeout(readTimeout);
        return clientHttpRequestFactory;
    }

    public List<Movie> getAllMovies(){
        RestTemplate restTemplate = restTemplate();

        ResponseEntity<List> response
                = restTemplate.getForEntity(apiEndpoint+allMoviesURI, List.class);

        List<Movie> movies = response.getBody();

        return movies;
    }

    public Movie getMovieById(String movieId) throws MovieRestClientException{
        RestTemplate restTemplate = restTemplate();
        Movie movie = null;
        try {
            ResponseEntity<Movie> response
                    = restTemplate.getForEntity(apiEndpoint + movieByIdURI.replaceAll(":id", movieId), Movie.class);

            movie = response.getBody();
        }catch(MovieRestClientException ex){
            logger.error("Status : " + ex.getStatusCode());
            throw ex;
        }
        return movie;
    }

    public List<Quote> getAllQuotesByMovie(String movieId) throws MovieRestClientException{
        RestTemplate restTemplate = restTemplate();
        List<Quote> quotes = null;
        try {
        ResponseEntity<List> response
                = restTemplate.getForEntity(apiEndpoint+quoteByIdURI.replaceAll(":id", movieId), List.class);

        quotes = response.getBody();
        }catch(MovieRestClientException ex){
            logger.error("Status : " + ex.getStatusCode());
            throw ex;
        }
        return quotes;
    }

}
