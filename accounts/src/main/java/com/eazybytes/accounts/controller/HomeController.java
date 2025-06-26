package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.entity.BaseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    @GetMapping("/health")
    public String health() {
        return "I am healthy";
    }

}
