package com.example.game.repositories;

import com.example.game.models.UserAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersAchievementsRepository extends JpaRepository<UserAchievement, Integer> {}
