package com.example.cinema_springboot.controller;

import com.example.cinema_springboot.model.entity.Cinema;
import com.example.cinema_springboot.service.impl.CinemaService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.Banner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/cinema")
@PreAuthorize("hasAuthority('ADMIN')")
public class CinemaController {

    CinemaService cinemaService;


    @GetMapping("/save")
    public String save(Model model) {
        Cinema cinema = new Cinema();
        model.addAttribute("cinema", cinema);
        return "cinema/cinema";

    }

    @PostMapping("/save_cinema")
    public String saveCinema(@ModelAttribute Cinema cinema, @RequestParam("file")MultipartFile multipartFile ) throws IOException {
        cinema.setImage(multipartFile.getBytes());
        cinemaService.save(cinema);
        return "redirect:/cinema/find_all";
    }

    @GetMapping("/find_all")
    public String findAll(Model model) {
        model.addAttribute("all_cinemas", cinemaService.findAll());
        return "cinema/all_cinemas";
    }

    @GetMapping("/find_by_id")
    public String findById(@RequestParam long id, Model model) {
        model.addAttribute("find_by_id", cinemaService.findById(id));
        return "cinema/find_by_id";
    }

    @GetMapping("/update/{cinema_id}")
    public String update(Model model, @PathVariable("cinema_id") long id) {
        Cinema cinema = cinemaService.findById(id);
        model.addAttribute("cinema", cinema);
        return "cinema/update_cinema";

    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Cinema cinema, @PathVariable Long id, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        cinema.setImage(multipartFile.getBytes());
        cinemaService.update(id, cinema);
        return "redirect:/cinema/find_all";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        cinemaService.deleteById(id);
        return "redirect:/cinema/find_all";

    }
    @GetMapping ("/find_by_name")
    public String findByName(Model model, @RequestParam(value = "text") String name) {
        model.addAttribute("cinema", cinemaService.findByName(name));
        return "cinema/cinema_find_name";
    }


    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") long id) {
        Cinema cinema = cinemaService.findById(id);
        if (cinema != null && cinema.getImage() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(cinema.getImage(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

        @GetMapping("/front_page")
        public String getFrontPage () {
            return "cinema/frontPage";
        }

        @GetMapping("/after")
        public String getAfter4 () {
            return "cinema/after4";
        }

        @GetMapping("/chan")
        public String getChan () {
            return "cinema/chan";

        }

        @GetMapping("/fast")
        public String getFast () {
            return "cinema/fast";
        }

    }



