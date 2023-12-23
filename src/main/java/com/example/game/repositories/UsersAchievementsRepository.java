package com.example.game.repositories;

import com.example.game.models.UserAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersAchievementsRepository extends JpaRepository<UserAchievement, Integer> {
    @Query(value = "SELECT SUM(rate) " +
            "FROM users_achievements JOIN achievements " +
            "ON achievement_id = id " +
            "WHERE user_id = :userId",
            nativeQuery = true)
    Integer getRating(@Param("userId") Integer userId);
}
