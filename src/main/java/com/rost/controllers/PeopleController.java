package com.rost.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rost.security.PersonDetails;
import com.rost.services.AdminService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PeopleController {
    private final AdminService adminService;

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/show-user-info")
    public ModelAndView showUserInfo() {
        /**
         * Spring достаёт объект из сессии и кладёт его в context.
         */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return new ModelAndView("user", "person", personDetails.getPrincipal());
    }

    @GetMapping("/admin")
    public ModelAndView adminPage() {
        adminService.doAdminStuff();
        return new ModelAndView("admin", "adminStuff", "adminStuffOnView");
    }
}
