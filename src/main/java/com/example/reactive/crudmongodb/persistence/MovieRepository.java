package com.example.reactive.crudmongodb.persistence;

import com.example.reactive.crudmongodb.model.Movie;
import java.util.List;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {

  @Query("{'movie.name': ?0}")
  Flux<Movie> findByMoviename(String movieName);
}
