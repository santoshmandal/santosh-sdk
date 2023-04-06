package com.movietest.api.movies.services;

import com.movietest.api.movies.entities.Movie;
import com.movietest.api.movies.entities.Quote;
import com.movietest.api.movies.repository.MovieRepository;
import com.movietest.api.movies.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private QuoteRepository quoteRepository;

    @Override
    public List<Movie> getAllMovies(int page) {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(String id) {
        return movieRepository.findByMovieId(id);
    }

    @Override
    public List<Quote> getAllQuotes(String movieId) {
        return quoteRepository.findByMovieId(movieId);
    }
}
