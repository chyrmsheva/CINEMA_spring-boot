package com.example.cinema_springboot.controller;

import com.example.cinema_springboot.model.entity.Place;
import com.example.cinema_springboot.model.entity.Room;
import com.example.cinema_springboot.service.impl.PlaceService;
import com.example.cinema_springboot.service.impl.RoomService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/place")
@PreAuthorize("hasAuthority('ADMIN')")
public class PlaceController {

    PlaceService placeService;

    RoomService roomService;


    @ModelAttribute("roomList")
    public List<Room> roomList() {
        return roomService.findAll();

    }

    @GetMapping("/save")
    public String save(Model model) {
        Place place = new Place();
        model.addAttribute("place", place);
        return "place/place";

    }

    @PostMapping("/save_place")
    public String saveMovie(@ModelAttribute Place place) {
        placeService.save(place);
        return "redirect:/place/buy";
    }

    @GetMapping("/find_all")
    public String findAll(Model model) {
        model.addAttribute("all_places", placeService.findAll());
        return "place/all_places";
    }


    @GetMapping("/find_by_id")
    public String findById(@RequestParam long id, Model model) {
        model.addAttribute("find_by_id", placeService.findById(id));
        return "place/find_by_id";
    }

    @GetMapping("/update/{place_id}")
    public String update(Model model, @PathVariable("place_id") long id) {

        Place place = placeService.findById(id);
        model.addAttribute("place", place);
        return "place/update_place";

    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Place place, @PathVariable long id) {
        placeService.update(id, place);
        return "redirect:/place/find_all";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        placeService.deleteById(id);
        return "redirect:/place/find_all";

    }

    @GetMapping("/buy")
    public String buy() {
        return "place/buy";
    }


}
