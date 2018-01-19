package com.example.Project.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin({"http://localhost:8100","file://"})
public class IndexController {

    public String index(){
        return "Sports Performance";
    }



}
