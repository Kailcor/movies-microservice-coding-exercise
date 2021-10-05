package com.veact.recruitment.api.repository;

import com.veact.recruitment.api.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
    Movie findMovieByTitle(String title);

    Page<Movie> findByYear(Integer year, Pageable pageable);

    @Override
    Page<Movie> findAll(Pageable pageable);

}
