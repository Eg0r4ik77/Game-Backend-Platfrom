package com.example.game.services;

import com.example.game.models.Achievement;
import com.example.game.models.User;
import com.example.game.models.UserAchievement;
import com.example.game.models.UserSubscription;
import com.example.game.repositories.AchievementRepository;
import com.example.game.repositories.UsersAchievementsRepository;
import com.example.game.repositories.UserRepository;
import com.example.game.repositories.UsersSubscriptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AchievementRepository achievementRepository;
    private final UsersAchievementsRepository usersAchievementsRepository;
    private final UsersSubscriptionsRepository usersSubscriptionsRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       AchievementRepository achievementRepository,
                       UsersAchievementsRepository userAchievementRepository,
                       UsersSubscriptionsRepository userSubscriptionRepository) {
        this.userRepository = userRepository;
        this.achievementRepository = achievementRepository;
        this.usersAchievementsRepository = userAchievementRepository;
        this.usersSubscriptionsRepository = userSubscriptionRepository;
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public void updateLogin(Integer userId, String newLogin){
        Optional<User> repositoryUser = userRepository.findById(userId);

        if(repositoryUser.isPresent()){
            User user = repositoryUser.get();
            user.setLogin(newLogin);
            userRepository.save(user);
        }
    }

    public void updatePassword(Integer userId, String newPassword){
        Optional<User> repositoryUser = userRepository.findById(userId);

        if(repositoryUser.isPresent()){
            User user = repositoryUser.get();
            user.setPassword(newPassword);
            userRepository.save(user);
        }
    }

    public List<Achievement> getAchievements(Integer userId){
        List<Achievement> achievements = new ArrayList<>();
        List<UserAchievement> usersAchievements = usersAchievementsRepository.findAll();

        for (UserAchievement userAchievement : usersAchievements) {
            Integer achievementUserId = userAchievement.getUserId();
            Integer achievementId = userAchievement.getAchievementId();

            if(Objects.equals(achievementUserId, userId)){
                Optional<Achievement> achievement = achievementRepository.findById(achievementId);
                achievement.ifPresent(achievements::add);
            }
        }

        return achievements;
    }

    public void setAchievement(Integer userId, Integer achievementId, String dateAchieved){
        Optional<User> repositoryUser = userRepository.findById(userId);
        Optional<Achievement> repositoryAchievement = achievementRepository.findById(achievementId);

        if(repositoryUser.isPresent() && repositoryAchievement.isPresent()){
            UserAchievement userAchievement = new UserAchievement(userId, achievementId, dateAchieved);
            usersAchievementsRepository.save(userAchievement);
        }
    }

    public void subscribe(Integer followerId, Integer followingId){
        Optional<User> repositoryFollower = userRepository.findById(followerId);
        Optional<User> repositoryFollowing = userRepository.findById(followingId);

        if(repositoryFollower.isPresent() && repositoryFollowing.isPresent()){
            UserSubscription userSubscription = new UserSubscription(followerId, followingId);
            usersSubscriptionsRepository.save(userSubscription);
        }
    }

    public void unsubscribe(Integer followerId, Integer followingId){
        for (UserSubscription subscription : usersSubscriptionsRepository.findAll()) {
            if(Objects.equals(followerId, subscription.getFollowerId())
            && Objects.equals(followingId, subscription.getFollowingId())){
                usersSubscriptionsRepository.delete(subscription);
            }
        }
    }

    public List<User> getFollowings(Integer userId){
        List<User> followings = new ArrayList<>();


        for (UserSubscription subscription : usersSubscriptionsRepository.findAll()) {
            if(Objects.equals(userId, subscription.getFollowerId())){
                Optional<User> repositoryFollowing = userRepository.findById(subscription.getFollowingId());
                repositoryFollowing.ifPresent(followings::add);
            }
        }

        return followings;
    }

    public List<User> getFollowers(Integer userId){
        List<User> followers = new ArrayList<>();

        for (UserSubscription subscription : usersSubscriptionsRepository.findAll()) {
            if(Objects.equals(userId, subscription.getFollowingId())){
                Optional<User> repositoryFollower = userRepository.findById(subscription.getFollowerId());
                repositoryFollower.ifPresent(followers::add);
            }
        }

        return followers;
    }
}