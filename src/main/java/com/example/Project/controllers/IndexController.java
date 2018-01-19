package com.example.Project.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin({"http://localhost:8100","file://"})
public class IndexController {

    @GetMapping
    public String index(){
        return "Sports Performance";
    }



}
