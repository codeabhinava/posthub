package com.example.posthub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.posthub.model.BotUser;

@Repository
public interface BotuserRepository extends JpaRepository<BotUser, Long> {

    BotUser findByName(String username);
}
