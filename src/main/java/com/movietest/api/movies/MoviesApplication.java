package com.movietest.api.movies;

import com.movietest.api.movies.client.MovieRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MoviesApplication {
    Logger logger = LoggerFactory.getLogger(MoviesApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MoviesApplication.class, args);
    }

    @Autowired
    private MovieRestClient movieRestClient;

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            //Only enable this to run the application in local server
            /*List<Movie> movies = movieRestClient.getAllMovies();
            logger.info("Movies List: "+movies);
            try {
                Movie movie = movieRestClient.getMovieById("4a110458c4235b39ea3e332b3f095ccca");
                logger.info("Movie : " + movie);
            }catch(MovieRestClientException ex){

            }
            try {
                List<Quote> quotes = movieRestClient.getAllQuotesByMovie("4a110458c4235b39ea3e332b3f095ccca");
                logger.info("Quotes List : " + quotes);
            }catch(MovieRestClientException ex){
            }
            try {
                Movie movie = movieRestClient.getMovieById("4a110458c4235b39ea3e332b3f095ccc");
                logger.info("Movie : " + movie);
            }catch(MovieRestClientException ex){

            }
            try {
                List<Quote> quotes = movieRestClient.getAllQuotesByMovie("4a110458c4235b39ea3e332b3f095ccc");
                logger.info("Quotes List : " + quotes);
            }catch(MovieRestClientException ex){
            }*/
        };
    }
}
