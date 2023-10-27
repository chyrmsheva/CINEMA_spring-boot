package com.example.cinema_springboot.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "rooms")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    long  id;
    String name;
    double rating;
    byte[] image;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH
    },fetch = FetchType.EAGER)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;
    @Transient
    private int cinemaId;

//    @Transient
//    private int cinemaID;


}
