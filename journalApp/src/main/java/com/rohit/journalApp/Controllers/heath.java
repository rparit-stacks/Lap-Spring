package com.rohit.journalApp.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class heath {

    @GetMapping("/ok")
    String healthCheck(){
        return "ok";
    }
}
