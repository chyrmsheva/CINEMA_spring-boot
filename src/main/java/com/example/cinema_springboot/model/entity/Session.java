package com.example.cinema_springboot.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "sessions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    long id;
    String name;
    LocalDateTime start= LocalDateTime.now();
    long duration;
    LocalDateTime finish=LocalDateTime.now().plusHours(duration);

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })@JoinColumn(name = "movie_id")
    private Movie movie;

    @Transient
    private int movieId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "session_and_room",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "session_id"))
    private List<Room> rooms;
}


