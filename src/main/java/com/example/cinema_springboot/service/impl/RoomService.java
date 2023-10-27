package com.example.cinema_springboot.service.impl;

import com.example.cinema_springboot.exception.EntityNotFoundException;
import com.example.cinema_springboot.model.entity.Room;
import com.example.cinema_springboot.repository.RoomRepository;
import com.example.cinema_springboot.service.ServiceLayer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RoomService implements ServiceLayer<Room> {


    RoomRepository roomRepository;

    @Override
    public void save(Room room) {
        roomRepository.save(room);


    }

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Movie with id=%id not found!!!", id)));
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public List<Room> findAllId(long id) {
        return (List<Room>) roomRepository.findAllId(id);
    }


//        return (List<Room>) entityManager.createQuery("from Room r where r.cinema.id =:id", Room.class).setParameter("id", id).getResultList();



    @Override
    public Room update(Long id, Room room) {

        Room oldRoom = findById(id);

        oldRoom.setName(room.getName());
        oldRoom.setRating(room.getRating());
        roomRepository.save(oldRoom);

        return oldRoom;
    }

    @Override
    public void deleteById(Long id) {
        roomRepository.deleteById(id);

    }
}
