package com.veact.recruitment.api.service;


import com.veact.recruitment.api.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {

    Movie getMovieByTitle(String title);
    Movie createMovie(Movie movie);
    Page<Movie> getMovies(Pageable paging, Integer year);

}
