package com.example.cinema_springboot.controller;

import com.example.cinema_springboot.model.entity.Movie;
import com.example.cinema_springboot.model.entity.Session;
import com.example.cinema_springboot.service.impl.MovieService;
import com.example.cinema_springboot.service.impl.SessionService;
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
@RequestMapping("/session")
@PreAuthorize("hasAuthority('ADMIN')")
public class SessionController {

    private final SessionService sessionService;
    private final MovieService movieService;



    @ModelAttribute("movieList")
    public List<Movie> movieList() {
        return movieService.findAll();
    }


    @GetMapping("/save")
    public String save(Model model){
        Session session = new Session();
        model.addAttribute("sess", session);
        return "session/save";

    }
    @PostMapping("/save_session")
    public String saveMovie(@ModelAttribute Session session){
        sessionService.save(session);
        return "redirect:/session/find_all";
    }

    @GetMapping("/find_all")
    public String findAll(Model model){
        model.addAttribute("all_session", sessionService.findAll());
        return "session/all_session";
    }
    @GetMapping("/findAllId/{id}")
    public String findAllId(@PathVariable("id") long id, Model model) {
        model.addAttribute("all_session_id",sessionService.findAllId(id));
        return "/session/all_session_id";
    }


    @GetMapping("/find_by_id")
    public String findById(@RequestParam("  ") long id, Model model){
        model.addAttribute("   ", sessionService.findById(id));
        return "   ";
    }

    @GetMapping("/update/{session_id}")
    public String update(Model model , @PathVariable("session_id") long id){

        Session session = sessionService.findById(id);
        model.addAttribute("session", session);
        return "session/update_session";

    }
    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Session session, @PathVariable long id){
        sessionService.update(id, session);
        return "redirect:/session/find_all";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id){
        sessionService.deleteById(id);
        return "redirect:/session/find_all";
    }
}
