package com.veact.recruitment.api.service;


import com.veact.recruitment.api.domain.Movie;

public interface MovieService {

    Movie getMovieByTitle(String title);
    Movie createMovie(Movie movie);

}
