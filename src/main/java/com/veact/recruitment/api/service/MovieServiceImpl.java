package com.veact.recruitment.api.service;

import com.veact.recruitment.api.domain.Movie;
import com.veact.recruitment.api.exception.NotFoundBusinessException;
import com.veact.recruitment.api.repository.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService
{

    private MovieRepository repository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        repository = movieRepository;
    }

    @Override
    public Movie getMovieByTitle(String title) {
        Movie movie = repository.findMovieByTitle(title);
        if(movie == null) {
            throw new NotFoundBusinessException("A movie with the title: " + title + " was not found in the database.");
        }
        return movie;
    }
}
