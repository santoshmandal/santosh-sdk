package com.movietest.api.movies.entities;

import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Basic(optional = false)
    @Column(name = "movie_id", unique=true)
    String movieId;

    @Basic(optional = false)
    @Column(name = "title")
    String title;

    @Basic(optional = false)
    @Column(name = "description")
    String description;

    @Basic(optional = false)
    @Column(name = "release")
    int release;

    @Basic(optional = false)
    @Column(name = "directors")
    String directors;

    @Basic(optional = false)
    @Column(name = "acast")
    String acast;

    @Basic(optional = false)
    @Column(name = "rating")
    double rating;

    @Basic(optional = false)
    @Column(name = "runtime")
    int runtime;

    @Basic(optional = false)
    @Column(name = "budget")
    int budget;

    @Basic(optional = false)
    @Column(name = "revenue")
    int revenue;

    @Basic(optional = false)
    @Column(name = "createdDate", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date createdDate;
}
