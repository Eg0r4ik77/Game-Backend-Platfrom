package com.example.game.services;

import com.example.game.models.User;
import com.example.game.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {
    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUp(String login, String password){
        User user = getUserByLogin(login);

        if(user == null){
            User newUser = new User();

            newUser.setLogin(login);
            newUser.setPassword(password);

            userRepository.save(newUser);

            return newUser;
        }

        return null;
    }

    public User signIn(String login, String password){
        User user = getUserByLogin(login);

        if(user != null && Objects.equals(user.getPassword(), password)){
            return user;
        }

        return null;
    }

    private User getUserByLogin(String login){
        return userRepository
                .findAll()
                .stream()
                .filter(repositoryUser -> repositoryUser.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }
}
