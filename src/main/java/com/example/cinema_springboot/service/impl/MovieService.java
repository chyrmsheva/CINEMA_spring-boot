package com.example.cinema_springboot.service.impl;

import com.example.cinema_springboot.exception.EntityNotFoundException;
import com.example.cinema_springboot.model.entity.Movie;
import com.example.cinema_springboot.repository.MovieRepository;
import com.example.cinema_springboot.service.ServiceLayer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MovieService implements ServiceLayer<Movie> {

    MovieRepository movieRepository;


    @Override
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException(String.format("Movie with id=%id not found!!!" , id)));
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie update(Long id, Movie movie) {

        Movie oldMovie = findById(id);

        oldMovie.setName(movie.getName());
        oldMovie.setMovieType(movie.getMovieType());
        oldMovie.setCreatedDate(movie.getCreatedDate());
        oldMovie.setCountry(movie.getCountry());
        oldMovie.setLanguage(movie.getLanguage());

        movieRepository.save(oldMovie);
        return oldMovie;

    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);

    }
}
