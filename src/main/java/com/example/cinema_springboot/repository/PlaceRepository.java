package com.example.cinema_springboot.repository;

import com.example.cinema_springboot.model.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place , Long> {
}
