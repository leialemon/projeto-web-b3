package tech.ada.jjh.homebroker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @GetMapping("/login")
    public String login() {
        return "login"; // Retorna a página de login
    }

    @GetMapping("/home")
    public String home() {
        return "home"; // Retorna a página inicial após login
    }
}
