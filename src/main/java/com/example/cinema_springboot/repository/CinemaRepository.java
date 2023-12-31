package com.example.cinema_springboot.repository;

import com.example.cinema_springboot.model.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    @Query("from Cinema c where c.name = :nameOfCinema")
    List<Cinema>findByName(String name);}


