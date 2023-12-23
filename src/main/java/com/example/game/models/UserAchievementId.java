package com.example.game.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class UserAchievementId implements Serializable {
    private Integer userId;
    private Integer achievementId;
}
