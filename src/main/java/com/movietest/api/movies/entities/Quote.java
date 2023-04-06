package com.movietest.api.movies.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Basic(optional = false)
    @Column(name = "movie_id")
    String movieId;

    @Basic(optional = false)
    @Column(name = "quote")
    String quote;
}
