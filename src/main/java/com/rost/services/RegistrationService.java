package com.rost.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rost.models.Person;
import com.rost.repositories.PeopleRepository;
import lombok.RequiredArgsConstructor;

import static com.rost.enums.Role.ROLE_USER;

@RequiredArgsConstructor
@Service
public class RegistrationService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional //поскольку происходит изменение в БД
    public void register(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole(ROLE_USER);
        peopleRepository.save(person);
    }
}
