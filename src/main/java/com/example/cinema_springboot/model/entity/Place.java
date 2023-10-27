package com.example.cinema_springboot.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "places")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    long id;
    int x;
    int y;
    int price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private Room room;
    @Transient
    private int roomId;

}
