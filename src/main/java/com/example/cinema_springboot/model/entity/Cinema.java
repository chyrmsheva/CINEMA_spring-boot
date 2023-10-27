package com.example.cinema_springboot.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cinemas")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String address;
    byte[] image;

    @OneToMany(
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH,
                    CascadeType.REMOVE
            }, fetch = FetchType.LAZY, mappedBy = "cinema")

    private List<Room> rooms;

}