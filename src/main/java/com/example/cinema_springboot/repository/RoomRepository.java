package com.example.cinema_springboot.repository;

import com.example.cinema_springboot.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room , Long> {

    @Query("from Room r where r.cinema.id =:id")
    List<Room> findAllId(long id);
}
