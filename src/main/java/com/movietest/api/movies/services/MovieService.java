package com.movietest.api.movies.services;

import com.movietest.api.movies.entities.Movie;
import com.movietest.api.movies.entities.Quote;

import java.util.List;

public interface MovieService {
    public List<Movie> getAllMovies(int page);
    public Movie getMovieById(String id);

    public List<Quote> getAllQuotes(String movieId);
}
