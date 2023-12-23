package com.example.game.controllers;

import com.example.game.models.Achievement;
import com.example.game.models.User;
import com.example.game.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> findUsers(){
        List<User> users = userService.findUsers();

        if(users.size() == 0){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/{userId}/achievements")
    public ResponseEntity<List<Achievement>> getAchievements(@PathVariable("userId") Integer userId){
        List<Achievement> achievements = userService.getAchievements(userId);

        if(achievements.size() == 0){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(achievements);
    }

    @GetMapping(value = "/{userId}/followings")
    public ResponseEntity<List<User>> getFollowings(@PathVariable("userId") Integer userId){
        List<User> followings = userService.getFollowings(userId);

        if(followings.size() == 0){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(followings);
    }

    @GetMapping(value = "/{userId}/followers")
    public ResponseEntity<List<User>> getFollowers(@PathVariable("userId") Integer userId){
        List<User> followers = userService.getFollowers(userId);

        if(followers.size() == 0){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(followers);
    }

    @PostMapping(value = "/{userId}/new-achievement/{achievementId}/{dateAchieved}")
    public ResponseEntity<String> setAchievement(@PathVariable("userId") Integer userId,
                                                 @PathVariable("achievementId") Integer achievementId,
                                                 @PathVariable("dateAchieved") String dateAchieved){
        userService.setAchievement(userId, achievementId, dateAchieved);
        return ResponseEntity.ok("New achievement!");
    }

    @PostMapping(value = "/{userId}/subscribe/{followingId}")
    public ResponseEntity<String> subscribe(@PathVariable("userId") Integer followerId,
                                            @PathVariable("followingId") Integer followingId){
        userService.subscribe(followerId, followingId);
        return ResponseEntity.ok("Subscribed successfully");
    }

    @PutMapping(value = "{userId}/update-login/{login}")
    public ResponseEntity<String> updateLogin(@PathVariable("userId") Integer userId,
                                              @PathVariable("login") String login){
        // Controller
        // length() > 6
        // length() <= 50
        // .matches("^[a-zA-Z0-9_.-]*$")
        // newLogin.charAt(newLogin.length()-1) != '.'
        // Character.isLetter(newLogin.charAt(0))
        // user in users ... !Objects.equals(currentUser.getLogin(), newLogin)

        userService.updateLogin(userId, login);
        return ResponseEntity.ok("Login updated successfully");
    }

    @PutMapping(value = "{userId}/update-password/{password}")
    public ResponseEntity<String> updatePassword(@PathVariable("userId") Integer userId, @PathVariable("password") String password){
        // Controller
        // length >= 8
        // new != old

        userService.updatePassword(userId, password);
        return ResponseEntity.ok("Password updated successfully");
    }

    @DeleteMapping(value = "{userId}/unsubscribe/{followingId}")
    public ResponseEntity<String> unsubscribe(@PathVariable("userId") Integer followerId,
                                            @PathVariable("followingId") Integer followingId){
        userService.unsubscribe(followerId, followingId);
        return ResponseEntity.ok("Unsubscribed successfully");
    }
}
