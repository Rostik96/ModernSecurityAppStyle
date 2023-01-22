package com.rost.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.rost.models.Person;
import com.rost.services.PeopleService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Person.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        peopleService.readByName(((Person) target).getUsername())
                .ifPresent(p -> errors.rejectValue(
                        "username",
                        "",
                        String.format("Username \"%s\" already exists.", p.getUsername())
                        )
                );
    }
}
