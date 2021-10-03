package com.veact.recruitment.api.controller;

import com.veact.recruitment.api.domain.Movie;
import com.veact.recruitment.api.service.MovieService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import static com.veact.recruitment.api.util.ValidationUtil.VALIDATION_MESSAGE_TITLE;
import static com.veact.recruitment.api.util.ValidationUtil.VALIDATION_REGEX_TITLE;

@Log4j2
@Validated
@RestController
@RequestMapping("/v1")
@Api(value = "movies", description = "The Movies service API")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
        log.info("Started Controller");
    }

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


}
