package com.example.cinema_springboot.model.entity;

import com.example.cinema_springboot.model.entity.enums.MovieType;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "movies")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    long id;
    String name;
    MovieType movieType;
    LocalDate createdDate ;
    String country;
    String language;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "movie")
    private List<Session> sessions;




}
