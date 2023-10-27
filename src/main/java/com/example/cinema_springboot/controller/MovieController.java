package com.example.cinema_springboot.controller;

import com.example.cinema_springboot.model.entity.Movie;
import com.example.cinema_springboot.model.entity.enums.MovieType;
import com.example.cinema_springboot.service.impl.MovieService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/movie")
@PreAuthorize("hasAuthority('ADMIN')")
public class MovieController {

    MovieService movieService;


    @GetMapping("/save")
    public String save(Model model) {
        MovieType[] movieTypes = MovieType.values();
        List<MovieType> genres = new ArrayList<>(Arrays.asList(movieTypes));
        Movie movie = new Movie();
        model.addAttribute("movie", movie);
        model.addAttribute("genres", genres);
        return "movie/movie";

    }

    @PostMapping("/save_movie")
    public String saveMovie(@ModelAttribute Movie movie) {
        movieService.save(movie);
        return "redirect:/movie/find_all";
    }

    @GetMapping("/find_all")
    public String findAll(Model model) {
        model.addAttribute("all_movies", movieService.findAll());
        return "movie/all_movies";
    }


    @GetMapping("/find_by_id")
    public String findById(@RequestParam long id, Model model) {
        model.addAttribute("find_by_id", movieService.findById(id));
        return "movie/find_by_id";
    }

    @GetMapping("/update/{movie_id}")
    public String update(Model model, @PathVariable("movie_id") long id) {
        Movie movie = movieService.findById(id);
        model.addAttribute("movie", movie);
        return "movie/update_movie";

    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Movie movie, @PathVariable long id) {
        movieService.update(id, movie);
        return "redirect:/movie/find_all";
    }


    @GetMapping("delete/{id}")
    public String delete(@PathVariable long id) {
        movieService.deleteById(id);
        return "redirect:/movie/find_all";
    }


}
