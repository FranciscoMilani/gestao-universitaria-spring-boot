package br.ucs.ffmilani.GestaoUni.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}