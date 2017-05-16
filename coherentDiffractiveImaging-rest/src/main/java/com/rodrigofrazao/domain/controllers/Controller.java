package com.rodrigofrazao.domain.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {


    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
