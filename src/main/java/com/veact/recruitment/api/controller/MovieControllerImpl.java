package com.veact.recruitment.api.controller;

import com.veact.recruitment.api.domain.Movie;
import com.veact.recruitment.api.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

import static com.veact.recruitment.api.util.ValidationUtil.VALIDATION_MESSAGE_TITLE;
import static com.veact.recruitment.api.util.ValidationUtil.VALIDATION_REGEX_TITLE;

@Log4j2
@Validated
@RestController
@RequestMapping("/v1")
public class MovieControllerImpl implements MovieController {

    private MovieService movieService;

    /**
     * Movie Controller Constructor.
     * @param movieService a MovieService.
     */
    public MovieControllerImpl(MovieService movieService) {
        this.movieService = movieService;
        log.info("Started Controller");
    }

    /**
     * Endpoint to handle the request of a movie by title.
     * @param title the title of the movie.
     * @return a Single movie that match exactly the name.
     */
    @Override
    @GetMapping(value = "/movie/{title}", produces = "application/json")
    public ResponseEntity<Movie> getMovie(
            @Valid
            @PathVariable(value = "title")
            @Pattern(regexp = VALIDATION_REGEX_TITLE, message = VALIDATION_MESSAGE_TITLE)
                    String title
    ){
        log.info("A user has requested for: {}.", title);
        Movie movie = movieService.getMovieByTitle(title);
        return ResponseEntity.ok(movie);
    }

    /**
     * Endpoint to create a new movie in the database.
     * @param movie the movie to be created.
     * @return the movie created.
     */
    @Override
    @PostMapping(value = "/movie", produces = "application/json")
    public ResponseEntity<Movie> getMovie(
            @RequestBody
                    Movie movie
    ){
        String title = movie== null? "" : movie.getTitle();
        log.info("Creating a movie: {}.", title);
        movie = movieService.createMovie(movie);
        return ResponseEntity.ok(movie);
    }


    /**
     * Endpoint to return a list of movies. Optionally can be filtered by year.
     * @param year (optional) year of the movie to filter in the list.
     * @param page (optional) page of the list to be returned (default 0)
     * @param size (optional) size of the list to be returned.
     * @return a list of movies in the database with pagination.
     */
    @Override
    @GetMapping("/movies")
    public ResponseEntity<Map<String, Object>> getAllMoviesPage(
            @RequestParam(required = false) Integer year,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Pageable paging = PageRequest.of(page, size);
        Page<Movie> movies = movieService.getMovies(paging,year);

        Map<String, Object> response = new HashMap<>();
        response.put("movies", movies.getContent());
        response.put("currentPage", movies.getNumber());
        response.put("totalItems", movies.getTotalElements());
        response.put("totalPages", movies.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);

    }



}
