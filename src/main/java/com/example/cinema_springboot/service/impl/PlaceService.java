package com.example.cinema_springboot.service.impl;

import com.example.cinema_springboot.exception.EntityNotFoundException;
import com.example.cinema_springboot.model.entity.Movie;
import com.example.cinema_springboot.model.entity.Place;
import com.example.cinema_springboot.repository.PlaceRepository;
import com.example.cinema_springboot.service.ServiceLayer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class PlaceService implements ServiceLayer<Place> {

    PlaceRepository placeRepository;

    @Override
    public void save(Place place) {
        placeRepository.save(place);

    }

    @Override
    public Place findById(Long id) {
        return placeRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException(String.format("Movie with id=%id not found!!!" ,id)));
    }

    @Override
    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    @Override
    public Place update(Long id, Place place) {

        Place oldPlace = findById(id);

        oldPlace.setY(place.getY());
        oldPlace.setX(place.getX());
        oldPlace.setPrice(place.getPrice());

        placeRepository.save(oldPlace);
        return oldPlace;
    }

    @Override
    public void deleteById(Long id) {
        placeRepository.deleteById(id);

    }
}
