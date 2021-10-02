package com.veact.recruitment.api.controller;

import com.veact.recruitment.api.domain.Movie;
import com.veact.recruitment.api.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;

import static com.veact.recruitment.api.util.ValidationUtil.VALIDATION_MESSAGE_TITLE;
import static com.veact.recruitment.api.util.ValidationUtil.VALIDATION_REGEX_TITLE;

@Validated
@RestController
@RequestMapping("/v1")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie/{title}")
    public ResponseEntity<Movie> getMovie(
            @Valid
            @PathVariable(value = "title")
            @Pattern(regexp = VALIDATION_REGEX_TITLE, message = VALIDATION_MESSAGE_TITLE)
            String title
    ){
        Movie movie = movieService.getMovieByTitle(title);
        return ResponseEntity.ok(movie);
    }


}
