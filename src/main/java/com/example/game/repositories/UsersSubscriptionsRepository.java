package com.example.game.repositories;

import com.example.game.models.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersSubscriptionsRepository extends JpaRepository<UserSubscription, Integer> {}