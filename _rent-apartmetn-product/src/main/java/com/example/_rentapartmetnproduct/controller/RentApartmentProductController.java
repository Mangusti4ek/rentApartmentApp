package com.example._rentapartmetnproduct.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentApartmentProductController {

    @GetMapping("/test")
    public String getTestString(){
        return "Успешно полученая строка";
    }

}
