package com.movietest.api.movies.controllers;

import com.movietest.api.movies.entities.Movie;
import com.movietest.api.movies.entities.Quote;
import com.movietest.api.movies.services.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/moviestore/v1/")
@EnableAutoConfiguration
@Slf4j
public class MovieController {
    Logger logger = LoggerFactory.getLogger(MovieController.class);
    @Autowired
    private MovieService movieService;
    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public ResponseEntity<List<Movie>> getAllMovies(@RequestParam(required = false) String page){
        int pageCount = 0;
        if (page != null) {
            try {
                pageCount = Integer.parseInt(page);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        if (pageCount < 0) {
            pageCount = 0;
        }

        List<Movie> movieList = movieService.getAllMovies(pageCount);
        return new ResponseEntity<List<Movie>>(movieList, HttpStatus.OK);
    }

    @RequestMapping(value = "/movie/{movieId}", method = RequestMethod.GET)
    public ResponseEntity<Movie> getMovieDetails(@PathVariable String movieId){
       Movie movie = movieService.getMovieById(movieId);
       HttpStatus status = HttpStatus.NOT_FOUND;
       if( movie != null ){
           status = HttpStatus.OK;
       }
       return new ResponseEntity<Movie>(movie, status);
    }

    @RequestMapping(value = "/movie/{movieId}/quote", method = RequestMethod.GET)
    public ResponseEntity<List<Quote>> getQuotes(@PathVariable String movieId){
        List<Quote> quoteList = movieService.getAllQuotes(movieId);
        HttpStatus status = HttpStatus.NOT_FOUND;
        if( quoteList != null && quoteList.size() > 0 ){
            status = HttpStatus.OK;
        }
        return new ResponseEntity<List<Quote>>(quoteList, status);
    }
}
