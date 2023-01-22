package com.rost.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rost.models.Person;
import com.rost.repositories.PeopleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RegistrationService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional //поскольку происходит изменение в БД
    public void register(Person person) {
        person.setPassword(
                passwordEncoder.encode(person.getPassword())
        );
        peopleRepository.save(person);
    }
}
