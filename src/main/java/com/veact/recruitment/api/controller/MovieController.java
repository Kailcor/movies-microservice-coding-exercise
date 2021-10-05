package com.veact.recruitment.api.controller;

import com.veact.recruitment.api.domain.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface MovieController {

    ResponseEntity<Movie> getMovie(String title);

    ResponseEntity<Movie> getMovie(@RequestBody Movie movie);

    ResponseEntity<Map<String, Object>> getAllMoviesPage(Integer year, int page, int size);
}
