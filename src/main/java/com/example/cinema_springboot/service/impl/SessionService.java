package com.example.cinema_springboot.service.impl;

import com.example.cinema_springboot.exception.EntityNotFoundException;
import com.example.cinema_springboot.model.entity.Session;
import com.example.cinema_springboot.repository.SessionRepository;
import com.example.cinema_springboot.service.ServiceLayer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SessionService implements ServiceLayer<Session> {

    SessionRepository sessionRepository;

    @Override
    public void save(Session session) {
        sessionRepository.save(session);

    }

    @Override
    public Session findById(Long id) {
        return sessionRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException(String.format("Movie with id=%id not found!!!" ,id)));

    }

    @Override
    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    public List<Session> findAllId(long id) {
        return (List<Session>) sessionRepository.findAllId(id);
    }

    @Override
    public Session update(Long id, Session session) {

        Session oldSession = findById(id);
        oldSession.setName(session.getName());
        oldSession.setStart(session.getStart());
        oldSession.setFinish(session.getFinish());
        oldSession.setDuration(session.getDuration());

        sessionRepository.save(oldSession);
        return oldSession;

    }

    @Override
    public void deleteById(Long id) {
        sessionRepository.deleteById(id);

    }
}
