package com.example.reactive.crudmongodb.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieEvent {

  private String movieId;
  private LocalDateTime when;
}
