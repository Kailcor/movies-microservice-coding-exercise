package com.veact.recruitment.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** Home redirection to OpenAPI api documentation. */
@RestController
public class HomeController {

    @GetMapping(value = "/")
    public String index() {
        return "redirect:swagger-ui.html";
    }
}
