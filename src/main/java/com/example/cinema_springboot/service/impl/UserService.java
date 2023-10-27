package com.example.cinema_springboot.service.impl;

import com.example.cinema_springboot.model.entity.security.Role;
import com.example.cinema_springboot.model.entity.security.User;
import com.example.cinema_springboot.repository.RoleRepository;
import com.example.cinema_springboot.repository.UserRepository;
import com.example.cinema_springboot.service.ServiceLayer;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService implements ServiceLayer<User> {

    @Autowired
    UserRepository userRepository;


    final RoleRepository roleRepository;

    final PasswordEncoder passwordEncoder;

    public UserService(RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(User user) {

        Role role = null;
        if (user.getName().equals("admin")) {
            role = roleRepository.findByName("ADMIN");
            if (role == null){
                role = new Role("ADMIN");
            }
        } else {
            role = roleRepository.findByName("USER");
            if (role == null) {
                role = new Role("USER");
            }
        }

        user.setRoles(new ArrayList<>(Collections.singletonList(role)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public User update(Long id, User user) {
        return null;
    }
}
