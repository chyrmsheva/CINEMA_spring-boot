package com.example.cinema_springboot.controller;

import com.example.cinema_springboot.model.entity.Cinema;
import com.example.cinema_springboot.model.entity.Room;
import com.example.cinema_springboot.service.impl.CinemaService;
import com.example.cinema_springboot.service.impl.RoomService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
import java.util.List;

@Controller
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/room")
@PreAuthorize("hasAuthority('ADMIN')")
public class RoomController {

    RoomService roomService;

    CinemaService cinemaService;

    @ModelAttribute("cinemaList")
    public List<Cinema> cinemaList() {
        return cinemaService.findAll();
    }

    @GetMapping("/save")
    public String save(Model model) {
        Room room = new Room();
        model.addAttribute("room", room);
        return "room/room";

    }

    @PostMapping("/save_room")
    public String saveRoom(@ModelAttribute Room room, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        room.setImage(multipartFile.getBytes());
        roomService.save(room);
        return "redirect:/room/find_all";
    }

    @GetMapping("/find_all")
    public String findAll(Model model) {
        model.addAttribute("all_rooms", roomService.findAll());
        return "room/all_rooms";
    }


    @GetMapping("/find_by_id")
    public String findById(@RequestParam long id, Model model) {
        model.addAttribute("find_by_id", roomService.findById(id));
        return "room/find_by_id";
    }

    @GetMapping("/findAllId/{id}")
    public String findAllId(@PathVariable("id") long id, Model model) {
        model.addAttribute("room_all_id", roomService.findAllId(id));
        return "/room/room_all_id";
    }

    @GetMapping("/update/{room_id}")
    public String update(Model model, @PathVariable("room_id") long id) {

        Room room = roomService.findById(id);
        model.addAttribute("room", room);
        return "room/update_room";

    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Room room, @PathVariable long id, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        room.setImage(multipartFile.getBytes());
        roomService.update(id, room);
        return "redirect:/room/find_all";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        roomService.deleteById(id);
        return "redirect:/room/find_all";
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") long id) {
        Room room = roomService.findById(id);
        if (room != null && room.getImage() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(room.getImage(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/vip_room")
    public String getFrontPage() {
        return "room/rooms";

    }

}
