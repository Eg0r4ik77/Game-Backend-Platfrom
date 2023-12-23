package com.example.game.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@IdClass(UserSubscriptionId.class)
@Table(name = "users_subscriptions")
public class UserSubscription {
    @Id
    @Column(name = "follower_id")
    private Integer followerId;

    @Id
    @Column(name = "following_id")
    private Integer followingId;
}
