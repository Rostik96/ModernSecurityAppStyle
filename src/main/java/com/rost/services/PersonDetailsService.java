package com.rost.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rost.repositories.PeopleRepository;
import com.rost.security.PersonDetails;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepository peopleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new PersonDetails(
                peopleRepository.findByName(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not fond"))
        );
    }
}
