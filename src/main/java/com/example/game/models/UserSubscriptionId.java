package com.example.game.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class UserSubscriptionId implements Serializable {
    private Integer followerId;
    private Integer followingId;
}
