package com.veact.recruitment.api.service;

import com.veact.recruitment.api.domain.Movie;
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
        return repository.findMovieByTitle(title);
    }
}
