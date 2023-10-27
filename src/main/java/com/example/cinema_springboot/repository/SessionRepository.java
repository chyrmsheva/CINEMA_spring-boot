package com.example.cinema_springboot.repository;

import com.example.cinema_springboot.model.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

   @Query("from Session s where s.movie.id =:id")
    List<Session> findAllId(long id);

}
