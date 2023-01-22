package com.rost.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rost.models.Person;
import com.rost.repositories.PeopleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    public Optional<Person> readByName(String username) {
        return peopleRepository.findByUsername(username);
    }
}
