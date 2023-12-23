package com.example.game.controllers;

import com.example.game.models.Achievement;
import com.example.game.models.User;
import com.example.game.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/sign-up/{login}/{password}")
    public ResponseEntity<User> signUp(@PathVariable("login") String login,
                                                             @PathVariable("password") String password){
        User user = authService.signUp(login, password);

        if(user == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/sign-in/{login}/{password}")
    public ResponseEntity<User> signIn(@PathVariable("login") String login,
                                       @PathVariable("password") String password){
        User user = authService.signIn(login, password);

        if(user == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(user);
    }
}
