package com.example.reactive.crudmongodb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.reactive.crudmongodb.model.Movie;
import com.example.reactive.crudmongodb.persistence.MovieRepository;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@Testcontainers
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
class CustomerRepositoryTest {

  private static final Logger logger = LoggerFactory.getLogger(CustomerRepositoryTest.class);


  @Container
  static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.6");
  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
  }

  @Autowired
  private MovieRepository movieRepository;

  @AfterEach
  void cleanUp() {
    this.movieRepository.deleteAll();
  }

  @Test
  void shouldReturnListOfMoviesWithMatchingName() throws InterruptedException {

    this.movieRepository.save(new Movie("Matrix"));

    Flux<Movie> movies = movieRepository.findByMoviename("Matrix");
    movies.subscribe(movie -> logger.info("{}", movie));

  }


}
