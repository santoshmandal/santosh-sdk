package com.movietest.api.movies.repository;


import com.movietest.api.movies.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository  extends JpaRepository<Movie, Integer> {
    Movie findByMovieId(String movieId);
}
