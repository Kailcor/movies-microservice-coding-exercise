package com.veact.recruitment.api.service;

import com.veact.recruitment.api.domain.Movie;
import com.veact.recruitment.api.exception.BadRequestBusinessException;
import com.veact.recruitment.api.exception.BusinessException;
import com.veact.recruitment.api.exception.NotFoundBusinessException;
import com.veact.recruitment.api.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService
{

    private static final String UNKNOWN_GENRE = "UNKNOWN";
    private final int DATE_FIRST_MOVIE_IN_HISTORY = 1895;
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

    @Override
    public Movie createMovie(Movie movie) {

        if(movie == null || movie.getTitle() == null || !StringUtils.hasText(movie.getTitle())){
            throw new BadRequestBusinessException("The title is required. Please provide a valid one");
        }

        if(movie.getYear() == null || movie.getYear() < DATE_FIRST_MOVIE_IN_HISTORY){
            throw new BadRequestBusinessException("The year is required. It should be greater than:"+ DATE_FIRST_MOVIE_IN_HISTORY);
        }

        if(movie.getAdult_content() == null) {
            movie.setAdult_content(false);
        }

        if(movie.getGenre() == null || !StringUtils.hasText(movie.getGenre())) {
            movie.setGenre(UNKNOWN_GENRE);
        }

        if(movie.getDescription() == null) {
            movie.setDescription("");
        }

        try {
            return repository.save(movie);
        } catch (Exception e) {
            log.error("It was not able to create the movie in the database: " + e.getMessage());
            throw new BusinessException("The movie can't be created, remember the title must be unique.");
        }
    }
}
