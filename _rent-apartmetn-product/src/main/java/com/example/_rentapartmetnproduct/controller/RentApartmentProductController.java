package com.example._rentapartmetnproduct.controller;

import com.example._rentapartmetnproduct.service.EmailSenderService;
import com.example._rentapartmetnproduct.service.ProductApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RentApartmentProductController {

    private final ProductApartmentService productApartmentService;
    private final EmailSenderService emailSenderService;

    @GetMapping("/test")
    public String getTestString(@RequestParam String mail, @RequestParam String message, @RequestParam String subject){
        emailSenderService.sendEmail(mail, message, subject);
        return "Успешно полученая строка";
    }

    @GetMapping("/product_check/{id}")
    public Long prepareProduct(@PathVariable Long id){

        return productApartmentService.prepareProduct(id);

    }

}
