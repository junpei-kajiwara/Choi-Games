package com.choigames.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choigames.app.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

  Optional<Player> findByEmail(String email);

  boolean existsByEmail(String email);

}
