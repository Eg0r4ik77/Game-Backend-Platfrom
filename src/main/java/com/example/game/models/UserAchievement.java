package com.example.game.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@IdClass(UserAchievementId.class)
@Table(name = "users_achievements")
public class UserAchievement{
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Id
    @Column(name = "achievement_id")
    private Integer achievementId;

    @Column(name = "date_achieved")
    private String dateAchieved;
}
